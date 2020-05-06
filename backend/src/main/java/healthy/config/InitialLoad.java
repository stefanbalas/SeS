package healthy.config;

import healthy.entity.LifestyleType;
import healthy.repository.LifestyleTypeRepository;
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
    LifestyleTypeRepository lifestyleTypeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        lifestyleTypeRepository.deleteAll();
        yamlConfig.getLifestyles().forEach((index, lifestyle) -> lifestyleTypeRepository.save(new LifestyleType(lifestyle.getLifestyleId(), lifestyle.getLifestyleName())));
    }
}
