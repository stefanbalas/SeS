package healthy.model;

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
}