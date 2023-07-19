package sg.edu.nus.iss.Workshop27.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSummary {
    
    private Integer game_id;
    private String name;
}
