package com.task.courseplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CoursePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursePlatformApplication.class, args);
	}

}
