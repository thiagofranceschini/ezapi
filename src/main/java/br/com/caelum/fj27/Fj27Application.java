package br.com.caelum.fj27;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class Fj27Application {

	public static void main(String[] args) {
		SpringApplication.run(Fj27Application.class, args);
	}
}
