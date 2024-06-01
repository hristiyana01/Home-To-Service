package Backend.hometoservice.authorization;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "ra.app")
public class AppProp {
    private String jwtSecret;
    private long jwtExpiration;
    private long jwtRefreshExpiration;
    private String baseUrl;
    private String googlePassword;
    private String clientId;
}