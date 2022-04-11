package com.microservices.citizenservice.controller;


import com.microservices.citizenservice.entity.Citizen;
import com.microservices.citizenservice.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenRepository citizenRepository; //the repository should be called in the service and the service  will be injected => bad practices !!

    @RequestMapping(path = "test")
    public ResponseEntity<String> testController() {
        return new ResponseEntity<>("hello World", HttpStatus.OK);
    }

    @RequestMapping(path = "/id/{id}")
    public ResponseEntity<java.util.List<Citizen>> findCitizensByVaccinationCenterId(@PathVariable Integer id) {

        List<Citizen> citizenList = citizenRepository.findByVaccinationCenterId(id);
        return new ResponseEntity<>(citizenList, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
        Citizen savedCitizen = citizenRepository.save(newCitizen);
        return new ResponseEntity<>(savedCitizen, HttpStatus.OK);
    }


}
