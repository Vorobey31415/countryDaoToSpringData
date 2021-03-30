package com.example.jdbctospringdata.demo.repositories;

import com.example.jdbctospringdata.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findByNameStartsWith(String text);

    Country findByCodeName(String codeName);

    Country findByName(String name);
}
