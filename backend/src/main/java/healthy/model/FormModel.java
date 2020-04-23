package healthy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormModel {
    int userId;
    String firstName;
    String lastName;
    String email;
    int age;
    char gender;
    float height;
    String lifestyle;
}