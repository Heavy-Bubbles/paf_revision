package sg.edu.nus.iss.Workshop26.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSummary {

    private Integer game_id;
    private String name;
}
