package healthy.controller;

import healthy.model.FormModel;
import healthy.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RestController {

    @Autowired
    FormService formService;

    @PostMapping(value = "/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody FormModel formModel) {
        return ResponseEntity.ok().body("Received request. Yay! :)");
    }
}
