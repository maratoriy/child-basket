package ru.childmarket.childmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import ru.childmarket.childmarket.configurations.ChildMarketConfiguration;

@SpringBootApplication
@Import({ChildMarketConfiguration.class})
public class ChildMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildMarketApplication.class, args);
    }

}
