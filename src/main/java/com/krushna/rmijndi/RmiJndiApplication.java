package com.krushna.rmijndi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krushna.rmijndi.server.Server;

@SpringBootApplication
public class RmiJndiApplication implements CommandLineRunner {
	

	@Autowired
	Server server;

	public static void main(String[] args) {
		SpringApplication.run(RmiJndiApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		server.startJNDI();
		
	}

}
