package healthy.model;

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
}