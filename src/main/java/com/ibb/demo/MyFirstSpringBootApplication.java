package com.ibb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ibb.*")
public class MyFirstSpringBootApplication {

	public static void main(String[] args) {
		          ApplicationContext ctx=SpringApplication.run(MyFirstSpringBootApplication.class, args);
                          
                          for(String str:ctx.getBeanDefinitionNames() ){
                              System.out.println(""+str);
                          }
	}

}
