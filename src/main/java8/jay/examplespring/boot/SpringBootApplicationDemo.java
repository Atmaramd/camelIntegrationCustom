package jay.examplespring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages ="jay.example.controller,jay.example")
public class SpringBootApplicationDemo {

	public static void main(String[] args) {
		System.out.println("Spring Boot Initited.");
		SpringApplication.run(SpringBootApplicationDemo.class, args);	
	}
}
