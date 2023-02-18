package jx.pgz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "music")
@Configuration
@Data
public class PropertiesConfiguration {

    private List<String> ignoreUrl;
    private String tokenKey;
    private int expireMinutes;

}
