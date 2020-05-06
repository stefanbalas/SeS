package healthy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityModel {
    private int userId;
    private float weight;
    private int step;
    private float water;
    private Long date;
}