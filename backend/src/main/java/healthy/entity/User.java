package healthy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
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

    public User(int userId) {
        this.userId = userId;
    }

    public User(String firstName, String lastName, String email, int age, char gender, float height, String lifestyle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.lifestyle = lifestyle;
    }
}