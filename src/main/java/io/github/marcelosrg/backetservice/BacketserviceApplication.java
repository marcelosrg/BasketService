package io.github.marcelosrg.backetservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class BacketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacketserviceApplication.class, args);
	}

}
