package com.java.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@ComponentScan("com.java")
public class GatewayApplication {
private static final Logger LOG =  LoggerFactory.getLogger(GatewayApplication.class);
	public static void main(String[] args) {
		System.out.println("TrainMember Application Started");
		SpringApplication springApplication = new SpringApplication(GatewayApplication.class);
		ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
		LOG.info("启动编译");
		LOG.info("网关地址: \thttp://127.0.0.1:{}/gateway", env.getProperty("server.port"));
	}
}
