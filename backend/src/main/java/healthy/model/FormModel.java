package healthy.model;

import healthy.entity.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormModel {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private char gender;
    private float height;
    private String lifestyle;

    public FormModel(Form form) {
        this.firstName = form.getFirstName();
        this.lastName = form.getLastName();
        this.email = form.getEmail();
        this.age = form.getAge();
        this.gender = form.getGender();
        this.height = form.getHeight();
        this.lifestyle = form.getLifestyle();
    }

    public void validate() {
        if (this.firstName == null) throw new IllegalArgumentException("firstName must be present");
        if (this.firstName.length() < 4) throw new IllegalArgumentException("firstName must have at least 4 characters");
        if (this.lastName == null) throw new IllegalArgumentException("lastName must be present");
        if (this.lastName.length() < 4) throw new IllegalArgumentException("lastName must have at least 4 characters");
        if (this.email == null) throw new IllegalArgumentException("email must be present");
        if (this.email.length() < 6) throw new IllegalArgumentException("email must have at least 6 characters");
        if (this.age == 0) throw new IllegalArgumentException("age must be present (or cannot be 0)");
        if (!Character.isLetter(this.gender)) throw new IllegalArgumentException("gender must be present");
        if (this.height == 0) throw new IllegalArgumentException("height must be present (or cannot be 0");
        if (this.lifestyle == null) throw new IllegalArgumentException("lifestyle must be present");
    }
}