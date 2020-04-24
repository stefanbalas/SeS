package healthy.controller;

import healthy.model.FormModel;
import healthy.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(maxAge = 3600)
@Controller
public class RestController {

    @Autowired
    FormService formService;

    @PostMapping(value = "/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody FormModel formModel) {
        try {
            formService.validateModel(formModel);
            formService.saveUserToDatabase(formModel);
            return ResponseEntity.ok().body("Saved user to database. Yay! :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
