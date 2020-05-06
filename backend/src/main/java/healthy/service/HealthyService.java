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

    public void checkIfEmailExists(String email) {
        if(userRepository.findByEmail(email).isPresent())
            throw new IllegalArgumentException("email already exists");
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
