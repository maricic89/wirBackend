package com.milos.wir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import com.milos.wir.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class WirApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirApplication.class, args);
	}

}
