package healthy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LifestyleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "lifestyle_id", nullable = false, unique = true)
    private int lifestyleId;

    @Column(name = "lifestyle_name", nullable = false, unique = true)
    public String lifestyleName;

    public LifestyleType(int lifestyleId, String lifestyleName) {
        this.lifestyleId = lifestyleId;
        this.lifestyleName = lifestyleName;
    }
}