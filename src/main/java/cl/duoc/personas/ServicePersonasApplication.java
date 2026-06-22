package cl.duoc.personas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "cl.duoc")
@EnableJpaRepositories(basePackages = "cl.duoc")
@EntityScan(basePackages = "cl.duoc")
public class ServicePersonasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePersonasApplication.class, args);
    }
}