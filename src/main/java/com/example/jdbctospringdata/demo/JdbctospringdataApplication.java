package com.example.jdbctospringdata.demo;

import com.example.jdbctospringdata.demo.model.Country;
import com.example.jdbctospringdata.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories
public class JdbctospringdataApplication {

	@Autowired
	private CountryRepository countryRepo;

	public static void main(String[] args) {
		SpringApplication.run(JdbctospringdataApplication.class, args);
	}

	@PostConstruct
	public void init() {
		countryRepo.save(new Country("Russia", "RU"));
		countryRepo.save(new Country("USA", "US"));
		countryRepo.save(new Country("England", "EN"));

		System.out.println("All saved countries:");
		countryRepo.findAll().forEach(System.out::println);

		List<Country> countryStartWithU = countryRepo.findByNameStartsWith("U");
		System.out.println("Country starts with U - " + countryStartWithU);


	}
}
