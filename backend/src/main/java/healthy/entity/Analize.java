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
public class Analize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "min_value", nullable = false)
    private float minValue;

    @Column(name = "max_value", nullable = false)
    private float maxValue;

    @Column(name = "date", nullable = false)
    public Date date;

    public Analize() {
    }

    public Analize(int id) {
        this.id = id;
    }

    public Analize(int userId, String name, float value, float minValue, float maxValue, Date date) {
        this.userId = userId;
        this.name = name;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.date = date;
    }
}