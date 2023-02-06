package io.test.barogo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.test.barogo.config.properties.SecurityProperties;
import io.test.barogo.config.redis.RedisPublisher;
import io.test.barogo.config.redis.RedisTopicContact;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final RedisPublisher redisPublisher;
    private final SecurityProperties securityProperties;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String tokenHeader = request.getHeader("Authorization");
        boolean isLogoutSuccess = false;
        Jws<Claims> jws = null;
        try {
            if(StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer")) {
                String jwtToken = tokenHeader.replace("Bearer ", "");
                jws = Jwts.parserBuilder()
                        .setSigningKey(securityProperties.getSecretKey())
                        .build()
                        .parseClaimsJws(jwtToken);
                String loginId = jws.getBody().getSubject();
                isLogoutSuccess = redisPublisher.remove(RedisTopicContact.getTopic(loginId));
            }
        } catch (Exception e) {
            throw ErrorResponse.of(ErrorCode.UNAUTHORIZED);
        }

        if (!isLogoutSuccess) {
            throw ErrorResponse.of(ErrorCode.UNAUTHORIZED);
        }
    }
}
