package healthy.config;

import healthy.entity.LifestyleType;
import healthy.repository.LifestyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
class InitialLoad implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    LifestyleRepository lifestyleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        lifestyleRepository.deleteAll();
        yamlConfig.getLifestyles().forEach((index, lifestyle) -> lifestyleRepository.save(new LifestyleType(lifestyle.getLifestyleId(), lifestyle.getLifestyleName())));
    }
}
