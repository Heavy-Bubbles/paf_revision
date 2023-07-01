package sg.edu.nus.iss.workshop21.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    
    private int status;
    private Date timeStamp;
    private String message;
}
