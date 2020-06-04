package healthy.entity;

import healthy.model.FormModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int userId;

    @Column(name = "firstName", nullable = false)
    public String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    public int age;

    @Column(name = "gender", nullable = false)
    public char gender;

    @Column(name = "height", nullable = false)
    public float height;

    @Column(name = "lifestyle", nullable = false)
    private String lifestyle;

    public Form() {
    }

    public Form(FormModel formModel) {
        this.firstName = formModel.getFirstName();
        this.lastName = formModel.getLastName();
        this.email = formModel.getEmail();
        this.age = formModel.getAge();
        this.gender = formModel.getGender();
        this.height = formModel.getHeight();
        this.lifestyle = formModel.getLifestyle();
    }
}