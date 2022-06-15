package br.com.poc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SpringCloudStreamMultipleRabbitmqPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamMultipleRabbitmqPocApplication.class, args);
	}

}
