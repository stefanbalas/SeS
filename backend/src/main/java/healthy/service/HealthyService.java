package healthy.service;

import healthy.entity.LifestyleType;
import healthy.entity.User;
import healthy.model.UserModel;
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

    public void saveUserToDatabase(UserModel userModel) {
        userRepository.save(mapModelToUser(userModel));
    }

    public void validateModel(UserModel userModel) {
        if (userModel.getFirstName() == null) throw new IllegalArgumentException("firstName must be present");
        if (userModel.getFirstName().length() < 4)
            throw new IllegalArgumentException("firstName must have at least 4 characters");
        if (userModel.getLastName() == null) throw new IllegalArgumentException("lastName must be present");
        if (userModel.getLastName().length() < 4)
            throw new IllegalArgumentException("lastName must have at least 4 characters");
        if (userModel.getEmail() == null) throw new IllegalArgumentException("email must be present");
        if (userModel.getEmail().length() < 6)
            throw new IllegalArgumentException("email must have at least 6 characters");
        if (checkIfEmailExists(userModel.getEmail())) throw new IllegalArgumentException("email already exists");
        if (userModel.getAge() == 0) throw new IllegalArgumentException("age must be present (or cannot be 0)");
        if (!Character.isLetter(userModel.getGender())) throw new IllegalArgumentException("gender must be present");
        if (userModel.getHeight() == 0) throw new IllegalArgumentException("height must be present (or cannot be 0");
        if (userModel.getLifestyle() == null) throw new IllegalArgumentException("lifestyle must be present");
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User mapModelToUser(UserModel userModel) {
        return User.builder()
                .firstName(userModel.getEmail())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .age(userModel.getAge())
                .gender(userModel.getGender())
                .height(userModel.getHeight())
                .lifestyle(userModel.getLifestyle()).build();
    }


    public List<String> getLifestyleTypes() {
        return lifestyleTypeRepository.findAll().stream().map(LifestyleType::getLifestyleName).collect(Collectors.toList());
    }
}
