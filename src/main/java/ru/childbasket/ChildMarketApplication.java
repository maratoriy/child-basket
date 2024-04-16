package ru.childbasket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.childbasket.configurations.ChildMarketConfiguration;

@SpringBootApplication
@OpenAPIDefinition
@Import({ChildMarketConfiguration.class})
public class ChildMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildMarketApplication.class, args);
    }

}
