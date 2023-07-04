package sg.edu.nus.iss.Workshop24.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.nus.iss.Workshop24.model.OrderDetails;
import sg.edu.nus.iss.Workshop24.model.Orders;


// to combine both components into 1 thing
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Orders order;
    private List<OrderDetails> orderDetails;
}
