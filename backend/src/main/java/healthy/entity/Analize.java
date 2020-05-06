package healthy.entity;

import healthy.model.AnalizeModel;
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

    public Analize(AnalizeModel analizeModel) {
        this.userId = analizeModel.getUserId();
        this.name = analizeModel.getName();
        this.value = analizeModel.getValue();
        this.minValue = analizeModel.getMinValue();
        this.maxValue = analizeModel.getMaxValue();
        this.date = new Date(analizeModel.getDate());
    }
}