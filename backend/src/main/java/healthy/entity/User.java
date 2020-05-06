package healthy.entity;

import healthy.model.UserModel;
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
public class User {

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

    public User() {
    }

    public User(UserModel userModel) {
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.email = userModel.getEmail();
        this.age = userModel.getAge();
        this.gender = userModel.getGender();
        this.height = userModel.getHeight();
        this.lifestyle = userModel.getLifestyle();
    }
}