package com.kalystee.kalconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class KalConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(KalConfigServerApplication.class, args);
	}

}
