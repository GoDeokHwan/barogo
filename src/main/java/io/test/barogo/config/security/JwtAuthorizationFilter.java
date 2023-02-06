package io.test.barogo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.test.barogo.config.properties.SecurityProperties;
import io.test.barogo.config.redis.RedisSubscriber;
import io.test.barogo.config.redis.RedisTopicContact;
import io.test.barogo.config.security.service.PrincipalDetailsService;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final SecurityProperties securityProperties;
    private final PrincipalDetailsService principalDetailsService;
    private final RedisSubscriber redisSubscriber;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, SecurityProperties securityProperties, PrincipalDetailsService principalDetailsService, RedisSubscriber redisSubscriber) {
        super(authenticationManager);
        this.securityProperties = securityProperties;
        this.principalDetailsService = principalDetailsService;
        this.redisSubscriber = redisSubscriber;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String tokenHeader = request.getHeader("Authorization");
            String jwtToken = null;

            if(StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer")) {
                jwtToken = tokenHeader.replace("Bearer ", "");
            }

            if(jwtToken != null && isValid(jwtToken)) {
                SecurityContextHolder.getContext().setAuthentication(getAuth(jwtToken));
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuth(String jwtToken) {
        PrincipalDetails user = (PrincipalDetails)principalDetailsService.loadUserByUsername(
                Jwts.parserBuilder()
                        .setSigningKey(securityProperties.getSecretKey())
                        .build()
                        .parseClaimsJws(jwtToken).getBody()
                        .getSubject()
        );
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    private boolean isValid(String jwtToken) {
        boolean ret = true;

        Jws<Claims> jws = null;

        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(securityProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(jwtToken);

            if( jws == null ||
                    jws.getBody().getSubject() == null ||
                    jws.getBody().getExpiration().before(new Date())) {
                ret = false;
            }

            String token = redisSubscriber.get(RedisTopicContact.getTopic(jws.getBody().getSubject())).toString();
            if (token == null && !token.equals(jwtToken)) {
                throw ErrorResponse.of(ErrorCode.UNAUTHORIZED);
            }
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }
}
