package com.kmdev.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    
	public static void main(String... args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
