package sg.edu.nus.iss.Workshop24.configuration;

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
        .title("My Day 24 Workshop API on Swagger")
        .description("PAF Day 24")
        .version("version 1.0"));
    }
}
