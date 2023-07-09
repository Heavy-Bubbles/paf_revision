package sg.edu.nus.iss.Workshop26.controller;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private int status;
    private Date timestamp;
    private String message;
    
}
