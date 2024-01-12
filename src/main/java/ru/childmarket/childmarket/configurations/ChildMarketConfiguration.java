package ru.childmarket.childmarket.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({
        "ru.childmarket.childmarket.domain"
})
@Import({
        WebSecurityConfiguration.class
})
public class ChildMarketConfiguration {
}
