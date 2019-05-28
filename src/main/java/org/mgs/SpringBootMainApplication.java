package org.mgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@SpringBootApplication
@Configuration
public class SpringBootMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApplication.class, args);
    }

    @Bean
    @DependsOn(value = {"customRestTemplateCustomizer"})
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }

    @Bean
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }
}
