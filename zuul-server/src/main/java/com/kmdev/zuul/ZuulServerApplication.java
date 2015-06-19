package com.kmdev.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@EnableZuulProxy
@SpringBootApplication
@Controller
public class ZuulServerApplication {

	public static void main(String... args) {
		 new SpringApplicationBuilder(ZuulServerApplication.class).web(true).run(args);
	}
	
}
