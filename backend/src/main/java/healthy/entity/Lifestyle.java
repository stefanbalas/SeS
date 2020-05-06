package healthy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Lifestyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "job_activity", nullable = false)
    public int jobActivity;

    @Column(name = "freetime_activity", nullable = false)
    private int freetimeActivity;

    @Column(name = "practises_sport", nullable = false)
    private int practicesSport;

    @Column(name = "timp_mediu_activitate", nullable = false)
    public int timpMediuActivitate;

    @Column(name = "timp_calculator", nullable = false)
    public int timpCalculator;

    public Lifestyle() {
    }

    public Lifestyle(int id) {
        this.id = id;
    }

    public Lifestyle(int userId, int jobActivity, int freetimeActivity, int practicesSport, int timpMediuActivitate, int timpCalculator) {
        this.userId = userId;
        this.jobActivity = jobActivity;
        this.freetimeActivity = freetimeActivity;
        this.practicesSport = practicesSport;
        this.timpMediuActivitate = timpMediuActivitate;
        this.timpCalculator = timpCalculator;
    }
}