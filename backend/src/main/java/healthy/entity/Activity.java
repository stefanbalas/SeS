package healthy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "weight", nullable = false)
    public float weight;

    @Column(name = "step", nullable = false)
    private int step;

    @Column(name = "water", nullable = false)
    private float water;

    @Column(name = "date", nullable = false)
    public Date date;

    public Activity() {
    }

    public Activity(int id) {
        this.id = id;
    }

    public Activity(int userId, float weight, int step, float water, Date date) {
        this.userId = userId;
        this.weight = weight;
        this.step = step;
        this.water = water;
        this.date = date;
    }
}