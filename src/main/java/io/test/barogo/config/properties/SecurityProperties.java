package io.test.barogo.config.properties;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private String secret;
    private Long expiration;

    public Key getSecretKey() {
        byte[] keyBytes = this.getSecret().getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
