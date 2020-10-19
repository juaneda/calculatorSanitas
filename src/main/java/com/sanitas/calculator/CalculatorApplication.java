package com.sanitas.calculator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("Desplegando microservicio en ip: " + InetAddress.getLocalHost().getHostAddress());
		SpringApplication.run(CalculatorApplication.class, args);
	}

}
