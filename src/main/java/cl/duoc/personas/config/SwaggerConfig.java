package cl.duoc.personas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Service Personas")
                        .description("Microservicio para administrar personas fallecidas")
                        .version("v1")
                        .contact(new Contact().name("Duoc UC").email("info@duoc.cl")));
    }
}
