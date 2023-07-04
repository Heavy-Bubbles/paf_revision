package sg.edu.nus.iss.Workshop24.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    
    private Integer id;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;

}
