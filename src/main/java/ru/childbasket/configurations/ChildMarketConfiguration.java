package ru.childbasket.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({
        "ru.childbasket.domain"
})
@Import({
        WebSecurityConfiguration.class
})
public class ChildMarketConfiguration {
}
