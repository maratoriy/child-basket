package ru.childbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.childbasket.configurations.ChildMarketConfiguration;

@SpringBootApplication
@Import({ChildMarketConfiguration.class})
public class ChildMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildMarketApplication.class, args);
    }

}
