package sg.edu.nus.iss.Workshop27.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Integer game_id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Double average;
    private Integer users_rated;
    private String url;
    private String thumbnail;

}
