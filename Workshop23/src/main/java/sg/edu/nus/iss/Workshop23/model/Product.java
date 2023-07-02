package sg.edu.nus.iss.Workshop23.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    private String name;
    private double costPrice;
}
