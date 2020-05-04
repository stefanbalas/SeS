package healthy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LifestyleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int userId;

    @Column(name = "lifestyle", nullable = false, unique = true)
    public String lifestyle;

    public LifestyleType(String lifestyle) {
        this.lifestyle = lifestyle;
    }
}