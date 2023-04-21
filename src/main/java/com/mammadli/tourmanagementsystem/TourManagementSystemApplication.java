package com.mammadli.tourmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourManagementSystemApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("tour_db");
		SpringApplication.run(TourManagementSystemApplication.class, args);
	}

}
