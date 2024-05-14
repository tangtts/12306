package com.java.train.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
@SpringBootApplication
@ComponentScan("com.java")
@MapperScan("com.java.train.*.mapper")
public class MemberApplication {
private static final Logger LOG =  LoggerFactory.getLogger(MemberApplication.class);
	public static void main(String[] args) {
		System.out.println("TrainMember Application Started");
		SpringApplication springApplication = new SpringApplication(MemberApplication.class);
		ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
		LOG.info("启动编译");
		LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
	}
}
