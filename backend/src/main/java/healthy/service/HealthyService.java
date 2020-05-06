package healthy.service;

import healthy.entity.LifestyleType;
import healthy.entity.User;
import healthy.model.FormModel;
import healthy.repository.LifestyleTypeRepository;
import healthy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthyService {

    private final UserRepository userRepository;
    private final LifestyleTypeRepository lifestyleTypeRepository;

    @Autowired
    public HealthyService(UserRepository userRepository, LifestyleTypeRepository lifestyleTypeRepository) {
        this.userRepository = userRepository;
        this.lifestyleTypeRepository = lifestyleTypeRepository;
    }

    public void saveUserToDatabase(FormModel formModel) {
        userRepository.save(mapModelToUser(formModel));
    }

    public void validateModel(FormModel formModel) {
        if (formModel.getFirstName() == null) throw new IllegalArgumentException("firstName must be present");
        if (formModel.getFirstName().length() < 4)
            throw new IllegalArgumentException("firstName must have at least 4 characters");
        if (formModel.getLastName() == null) throw new IllegalArgumentException("lastName must be present");
        if (formModel.getLastName().length() < 4)
            throw new IllegalArgumentException("lastName must have at least 4 characters");
        if (formModel.getEmail() == null) throw new IllegalArgumentException("email must be present");
        if (formModel.getEmail().length() < 6)
            throw new IllegalArgumentException("email must have at least 6 characters");
        if (checkIfEmailExists(formModel.getEmail())) throw new IllegalArgumentException("email already exists");
        if (formModel.getAge() == 0) throw new IllegalArgumentException("age must be present (or cannot be 0)");
        if (!Character.isLetter(formModel.getGender())) throw new IllegalArgumentException("gender must be present");
        if (formModel.getHeight() == 0) throw new IllegalArgumentException("height must be present (or cannot be 0");
        if (formModel.getLifestyle() == null) throw new IllegalArgumentException("lifestyle must be present");
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User mapModelToUser(FormModel formModel) {
        return User.builder()
                .firstName(formModel.getEmail())
                .lastName(formModel.getLastName())
                .email(formModel.getEmail())
                .age(formModel.getAge())
                .gender(formModel.getGender())
                .height(formModel.getHeight())
                .lifestyle(formModel.getLifestyle()).build();
    }


    public List<String> getLifestyleTypes() {
        return lifestyleTypeRepository.findAll().stream().map(LifestyleType::getLifestyleName).collect(Collectors.toList());
    }
}
