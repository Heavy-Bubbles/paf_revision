package sg.edu.nus.iss.Workshop27.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String user;
    private Integer rating;
    private String comment;
    private Integer gid;
    private Date posted;
    private String name;
    
}
