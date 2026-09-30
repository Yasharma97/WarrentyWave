package com.abes.warrentyWave.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.abes.warrentyWave")
@EnableJpaRepositories(basePackages = "com.abes.warrentyWave.repository")
@EntityScan(basePackages = "com.abes.warrentyWave.entity")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
