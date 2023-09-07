package com.watch.store;

import com.watch.store.dto.StrapDto;
import com.watch.store.service.StrapService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("print");
	}

}
