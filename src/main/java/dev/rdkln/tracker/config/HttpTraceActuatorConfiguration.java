package dev.rdkln.tracker.config;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class HttpTraceActuatorConfiguration {


    @Bean
    public HttpExchangeRepository createTraceRepository(){
        return new InMemoryHttpExchangeRepository();
    }
}
