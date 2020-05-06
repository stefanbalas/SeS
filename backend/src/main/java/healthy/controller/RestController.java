package healthy.controller;

import healthy.model.*;
import healthy.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@Controller
public class RestController {

    @Autowired
    HealthyService healthyService;

    @PostMapping(value = "/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody UserModel userModel) {
        try {
            userModel.validate();
            healthyService.checkIfEmailExists(userModel.getEmail());
            healthyService.saveUserToDatabase(userModel);
            return ResponseEntity.ok().body("Saved user to database. Yay! :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/saveLifestyle")
    public ResponseEntity<String> saveLifestyle(@RequestBody LifestyleModel lifestyleModel) {
        healthyService.saveLifestyleToDatabase(lifestyleModel);
        return ResponseEntity.ok().body("Saved lifestyle to database. Yay! :)");
    }

    @PostMapping(value = "/saveAnalize")
    public ResponseEntity<String> saveAnalize(@RequestBody AnalizeModel analizeModel) {
        healthyService.saveAnalizeToDatabase(analizeModel);
        return ResponseEntity.ok().body("Saved analize to database. Yay! :)");
    }

    @PostMapping(value = "/saveHistory")
    public ResponseEntity<String> saveHistory(@RequestBody HistoryModel historyModel) {
        healthyService.saveHistoryToDatabase(historyModel);
        return ResponseEntity.ok().body("Saved history to database. Yay! :)");
    }

    @PostMapping(value = "/saveActivity")
    public ResponseEntity<String> saveActivity(@RequestBody ActivityModel activityModel) {
        healthyService.saveActivityToDatabase(activityModel);
        return ResponseEntity.ok().body("Saved activity to database. Yay! :)");
    }


    @GetMapping(value = "/getLifestyleTypes")
    public ResponseEntity<List<String>> getLifestyleTypes() {
        return ResponseEntity.ok().body(healthyService.getLifestyleTypes());
    }

    @GetMapping(value = "/getActivityByUserId/{id}")
    public ResponseEntity<List<ActivityModel>> getActivityByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(healthyService.getActivityByUserId(id));
    }
}
