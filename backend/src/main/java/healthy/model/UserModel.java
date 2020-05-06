package healthy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private char gender;
    private float height;
    private String lifestyle;
}