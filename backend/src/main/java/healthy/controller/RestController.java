package healthy.controller;

import healthy.model.*;
import healthy.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@Controller
public class RestController {

    @Autowired
    HealthyService healthyService;

    @PostMapping(value = "/submitForm")
    public ResponseEntity<?> submitForm(@RequestBody FormModel formModel) {
        try {
            formModel.validate();
            healthyService.checkIfEmailExists(formModel.getEmail());
            healthyService.saveFormToDatabase(formModel);
            return ResponseEntity.ok().body("Saved user to database. Yay! :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getAllForm")
    public ResponseEntity<List<FormModel>> getAllForm() {
        return ResponseEntity.ok().body(healthyService.getAllForm());

    }

    @PostMapping(value = "/saveLifestyle")
    public ResponseEntity<String> saveLifestyle(@RequestBody LifestyleModel lifestyleModel) {
        healthyService.saveLifestyleToDatabase(lifestyleModel);
        return ResponseEntity.ok().body("Saved lifestyle to database. Yay! :)");
    }

    @GetMapping(value = "/getAllLifestyle")
    public ResponseEntity<List<LifestyleModel>> getAllLifestyle() {
        return ResponseEntity.ok().body(healthyService.getAllLifestyle());
    }

    @PostMapping(value = "/saveAnalize")
    public ResponseEntity<String> saveAnalize(@RequestBody AnalizeModel analizeModel) {
        healthyService.saveAnalizeToDatabase(analizeModel);
        return ResponseEntity.ok().body("Saved analize to database. Yay! :)");
    }

    @GetMapping(value = "/getAllAnalize")
    public ResponseEntity<List<AnalizeModel>> getAllAnalize() {
        return ResponseEntity.ok().body(healthyService.getAllAnalize());
    }

    @PostMapping(value = "/saveHistory")
    public ResponseEntity<String> saveHistory(@RequestBody HistoryModel historyModel) {
        healthyService.saveHistoryToDatabase(historyModel);
        return ResponseEntity.ok().body("Saved history to database. Yay! :)");
    }

    @GetMapping(value = "/getAllHistory")
    public ResponseEntity<List<HistoryModel>> getAllHistory() {
        return ResponseEntity.ok().body(healthyService.getAllHistory());
    }

    @PostMapping(value = "/saveActivity")
    public ResponseEntity<String> saveActivity(@RequestBody ActivityModel activityModel) {
        healthyService.saveActivityToDatabase(activityModel);
        return ResponseEntity.ok().body("Saved activity to database. Yay! :)");
    }

    @GetMapping(value = "/getAllActivity")
    public ResponseEntity<List<ActivityModel>> getAllActivity() {
        return ResponseEntity.ok().body(healthyService.getAllActivities());
    }


    @GetMapping(value = "/getLifestyleTypes")
    public ResponseEntity<List<String>> getLifestyleTypes() {
        return ResponseEntity.ok().body(healthyService.getLifestyleTypes());
    }
}
