package sg.edu.nus.iss.Workshop26.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    
    private String game_id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Double average;
    private Integer users_rated;
    private String url;
    private String thumbnail;
    private Date timestamp;
}
