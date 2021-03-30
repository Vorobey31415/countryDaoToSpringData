package com.example.jdbctospringdata.demo.controllers;

import com.example.jdbctospringdata.demo.model.Country;
import com.example.jdbctospringdata.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private CountryRepository countryRepo;

    @Autowired
    public CountryController(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/country")
    public Country create(@RequestBody Country country) {
        return countryRepo.save(new Country(country.getName(), country.getCodeName()));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("api/country/{id}")
    public void changeNameById(@PathVariable("id") int id, @RequestParam("name") String name) {
        Country country = countryRepo.findById(id).orElse(null);
        if (country == null) {
            throw new NotFoundException(id);
        }
        country.setName(name);
        countryRepo.save(country);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/countries")
    public List<Country> getAll() {
        return countryRepo.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/country/{id}")
    public Country getById(@PathVariable("id") int id) {
        Country country = countryRepo.findById(id).orElse(null);
        if (country == null) {
            throw new NotFoundException(id);
        }
        return country;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("api/country/{id}")
    public void delete(@PathVariable("id") int id) {
        countryRepo.deleteById(id);
    }


}
