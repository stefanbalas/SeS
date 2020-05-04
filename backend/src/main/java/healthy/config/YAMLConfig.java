package healthy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("ses")
@Getter
@Setter
public class YAMLConfig {
    private List<String> lifestyles = new ArrayList<>();
}


