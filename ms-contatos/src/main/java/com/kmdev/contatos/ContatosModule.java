package com.kmdev.contatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import com.kmdev.wbus.EventStore;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
public class ContatosModule {

	@Bean
    public Environment env() {
        return new Environment();
    }
	
	@Bean
	public Reactor reactor(Environment env) {
		return Reactors.reactor().env(env)
				.dispatcher(Environment.RING_BUFFER)
				.get();
	}
	
	@Bean
    public EventStore eventStore() {
        return new EventStore(ContatosModule.class);
    }
	
    public static void main(String... args) {
    	SpringApplication.run(ContatosModule.class, args);
    }

}
