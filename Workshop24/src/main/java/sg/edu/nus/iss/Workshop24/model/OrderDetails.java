package sg.edu.nus.iss.Workshop24.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    
    private Integer id;
    private String product;
    private Double unitPrice;
    private Double discount;
    private Integer quantity;
    private Integer orderId;
    
}
