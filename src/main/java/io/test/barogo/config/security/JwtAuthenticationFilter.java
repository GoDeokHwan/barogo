package io.test.barogo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.test.barogo.config.properties.SecurityProperties;
import io.test.barogo.config.redis.RedisPublisher;
import io.test.barogo.config.redis.RedisTopicContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final SecurityProperties securityProperties;
    private final RedisPublisher redisPublisher;


    public JwtAuthenticationFilter(SecurityProperties securityProperties, RedisPublisher redisPublisher) {
        this.securityProperties = securityProperties;
        this.redisPublisher = redisPublisher;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("--JWT AUTHENTICATION FILTER--");

        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLoginId()
                            , loginRequest.getPassword()
                    ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String loginId = ((PrincipalDetails)authResult.getPrincipal()).getUsername();

        String jwtToken = Jwts.builder()
                .setSubject(loginId)
                .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getExpiration()))
                .signWith(securityProperties.getSecretKey(), SignatureAlgorithm.HS256)
                .compact();


        redisPublisher.publish(RedisTopicContact.getTopic(loginId), jwtToken, RedisTopicContact.getOneHourTime());

        response.addHeader("token", jwtToken);
    }
}
