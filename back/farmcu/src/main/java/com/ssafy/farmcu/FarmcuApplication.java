package com.ssafy.farmcu;

import com.ssafy.farmcu.config.properties.AppProperties;
import com.ssafy.farmcu.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
@SpringBootApplication
public class FarmcuApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmcuApplication.class, args);
	}

}