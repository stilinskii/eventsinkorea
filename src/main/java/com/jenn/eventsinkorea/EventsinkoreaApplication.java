package com.jenn.eventsinkorea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:aws.properties")
public class EventsinkoreaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsinkoreaApplication.class, args);
	}

}
