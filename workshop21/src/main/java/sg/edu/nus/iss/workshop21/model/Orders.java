package sg.edu.nus.iss.workshop21.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private Integer id;

    private Integer customerId;

    private String orderDate;
    
}
