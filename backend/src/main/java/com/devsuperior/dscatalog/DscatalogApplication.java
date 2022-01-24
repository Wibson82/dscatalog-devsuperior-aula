package com.devsuperior.dscatalog;

import com.devsuperior.dscatalog.services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscatalogApplication {

	@Autowired
	private CategoryRepository categoriaRepository;
	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(DscatalogApplication.class, args);
	}


}
