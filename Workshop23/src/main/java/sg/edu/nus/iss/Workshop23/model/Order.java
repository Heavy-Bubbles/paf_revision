package sg.edu.nus.iss.Workshop23.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;
    private String orderDate;
    private Integer customerId;
    private double totalPrice;
    private List<Product> products;
    private double totalCostPrice;

}
