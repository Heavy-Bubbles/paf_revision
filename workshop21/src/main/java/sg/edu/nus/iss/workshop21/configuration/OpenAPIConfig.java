package sg.edu.nus.iss.workshop21.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
    
     @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
        .info(new Info()
        .title("My PAF Workshop 21 API on Swagger")
        .description("PAF Workshop 21")
        .version("version 1.0"));
    }
}
