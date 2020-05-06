package healthy.service;

import healthy.entity.*;
import healthy.model.*;
import healthy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthyService {
    private final LifestyleTypeRepository lifestyleTypeRepository;
    private final LifestyleRepository lifestyleRepository;
    private final ActivityRepository activityRepository;
    private final AnalizeRepository analizeRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Autowired
    public HealthyService(LifestyleRepository lifestyleRepository, LifestyleTypeRepository lifestyleTypeRepository,
                          ActivityRepository activityRepository, AnalizeRepository analizeRepository,
                          HistoryRepository historyRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.lifestyleTypeRepository = lifestyleTypeRepository;
        this.activityRepository = activityRepository;
        this.analizeRepository = analizeRepository;
        this.historyRepository = historyRepository;
        this.lifestyleRepository = lifestyleRepository;
    }

    public void saveUserToDatabase(UserModel userModel) {
        userRepository.save(new User(userModel));
    }

    public void saveLifestyleToDatabase(LifestyleModel lifestyleModel) {
        lifestyleRepository.save(new Lifestyle(lifestyleModel));
    }

    public void saveAnalizeToDatabase(AnalizeModel analizeModel) {
        analizeRepository.save(new Analize(analizeModel));
    }

    public void saveHistoryToDatabase(HistoryModel historyModel) {
        historyRepository.save(new History(historyModel));
    }

    public void saveActivityToDatabase(ActivityModel activityModel) {
        activityRepository.save(new Activity(activityModel));
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


    public List<String> getLifestyleTypes() {
        return lifestyleTypeRepository.findAll().stream().map(LifestyleType::getLifestyleName).collect(Collectors.toList());
    }

    public List<ActivityModel> getActivityByUserId(int id) {
        return activityRepository.findAllByUserId(id).stream()
                .map(ActivityModel::new)
                .collect(Collectors.toList());
    }
}
