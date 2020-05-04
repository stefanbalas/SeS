package healthy.controller;

import healthy.model.FormModel;
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
    public ResponseEntity<String> submitForm(@RequestBody FormModel formModel) {
        try {
            healthyService.validateModel(formModel);
            healthyService.saveUserToDatabase(formModel);
            return ResponseEntity.ok().body("Saved user to database. Yay! :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getLifestyleTypes")
    public ResponseEntity<List<String>> getLifestyleTypes() {
        return ResponseEntity.ok().body(healthyService.getLifestyleTypes());
    }
}
