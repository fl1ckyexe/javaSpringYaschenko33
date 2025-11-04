package app.config;

import app.model.Apartment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {


    @Bean
    public Apartment defaultApartment() {
        return new Apartment(0L, "Demo Apartment", "Unknown Address", 0.0, "system");
    }


    @Bean
    @Scope("prototype")
    public Apartment newApartment() {
        return new Apartment();
    }
}
