package healthy.model;

import healthy.entity.Analize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalizeModel {
    private int userId;
    private String name;
    private float value;
    private float minValue;
    private float maxValue;
    private Long date;

    public AnalizeModel(Analize analize) {
        this.userId = analize.getUserId();
        this.name = analize.getName();
        this.value = analize.getValue();
        this.minValue = analize.getMinValue();
        this.maxValue = analize.getMaxValue();
        this.date = analize.getDate().getTime();
    }
}