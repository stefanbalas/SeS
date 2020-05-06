package healthy.model;

import healthy.entity.Activity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityModel {
    private int userId;
    private float weight;
    private int step;
    private float water;
    private Long date;

    public ActivityModel(Activity activity) {
        this.userId = activity.getUserId();
        this.weight = activity.getWeight();
        this.step = activity.getStep();
        this.water = activity.getWater();
        this.date = activity.getDate().getTime();
    }
}