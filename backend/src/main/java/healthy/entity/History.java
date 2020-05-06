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
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "last_visit", nullable = false)
    private Date lastVisit;

    @Column(name = "recommend_visit", nullable = false)
    private Date recommendVisit;

    public History() {
    }

    public History(int id) {
        this.id = id;
    }

    public History(int userId, String name, Date lastVisit, Date recommendVisit) {
        this.userId = userId;
        this.name = name;
        this.lastVisit = lastVisit;
        this.recommendVisit = recommendVisit;
    }
}