package gg.abdiel.clip.simplerest;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories(basePackages = "gg.abdiel.clip.simplerest.repo")
@EntityScan(basePackages = "gg.abdiel.clip.simplerest.entity")
public class SimpleRestApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SimpleRestApp.class);
        Properties props = new Properties();
        props.setProperty("spring.jpa.show-sql", "true");
        props.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
        app.setDefaultProperties(props);
        app.run(args);
    }
}