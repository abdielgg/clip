package gg.abdiel.clip.simplerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories(basePackages = "gg.abdiel.clip.simplerest.repo.*")
@EntityScan(basePackages = "gg.abdiel.clip.simplerest.entity.*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}