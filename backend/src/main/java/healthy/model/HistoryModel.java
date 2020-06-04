package healthy.model;

import healthy.entity.History;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryModel {
    private int userId;
    private String name;
    private Long lastVisit;
    private Long recommendVisit;

    public HistoryModel(History history) {
        this.userId = history.getUserId();
        this.name = history.getName();
        this.lastVisit = history.getLastVisit().getTime();
        this.recommendVisit = history.getRecommendVisit().getTime();
    }

}