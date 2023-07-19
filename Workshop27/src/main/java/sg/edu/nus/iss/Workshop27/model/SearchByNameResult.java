package sg.edu.nus.iss.Workshop27.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchByNameResult {

    private List<GameSummary> games;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Date timestamp;
    
}
