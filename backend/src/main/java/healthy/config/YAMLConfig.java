package healthy.config;

import healthy.entity.LifestyleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("ses")
@Getter
@Setter
public class YAMLConfig {
    private Map<String, LifestyleType> lifestyles = new HashMap<>();
}


