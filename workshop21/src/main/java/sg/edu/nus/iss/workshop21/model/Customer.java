package sg.edu.nus.iss.workshop21.model;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer id;

    private String firstName;

    private String lastName;

    private String company;

    public JsonObjectBuilder toJson(){
        return Json.createObjectBuilder()
            .add("id", this.getId())
            .add("first_name", this.getFirstName())
            .add("last_name", this.getLastName())
            .add("company", this.getCompany());
    }
    
}
