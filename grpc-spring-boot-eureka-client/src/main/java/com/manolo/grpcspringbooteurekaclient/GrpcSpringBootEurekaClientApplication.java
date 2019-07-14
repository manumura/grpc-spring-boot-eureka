package com.manolo.grpcspringbooteurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GrpcSpringBootEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcSpringBootEurekaClientApplication.class, args);
	}

}
