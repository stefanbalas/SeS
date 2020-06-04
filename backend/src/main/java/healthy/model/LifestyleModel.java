package healthy.model;

import healthy.entity.Lifestyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LifestyleModel {
    private int userId;
    private int jobActivity;
    private int freetimeActivity;
    private int practicesSport;
    private int timpMediuActivitate;
    private int timpCalculator;

    public LifestyleModel(Lifestyle lifestyle) {
        this.userId = lifestyle.getUserId();
        this.jobActivity = lifestyle.getJobActivity();
        this.freetimeActivity = lifestyle.getFreetimeActivity();
        this.practicesSport = lifestyle.getPracticesSport();
        this.timpMediuActivitate = lifestyle.getTimpMediuActivitate();
        this.timpCalculator = lifestyle.getTimpCalculator();
    }
}