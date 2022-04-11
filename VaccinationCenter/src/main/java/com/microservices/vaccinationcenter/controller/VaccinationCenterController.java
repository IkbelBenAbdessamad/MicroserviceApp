package com.microservices.vaccinationcenter.controller;


import com.microservices.vaccinationcenter.entity.VaccinationCenter;
import com.microservices.vaccinationcenter.model.Citizen;
import com.microservices.vaccinationcenter.model.RequiredResponse;
import com.microservices.vaccinationcenter.repository.CenterRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {

    @Autowired
    private CenterRepo centerRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/add")
    public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter) {
        VaccinationCenter savedCenter = centerRepo.save(vaccinationCenter);
        return new ResponseEntity<>(savedCenter, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethodWhenCitizenServiceIsDown")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id) {

        RequiredResponse requiredResponse = new RequiredResponse();

        //Get vaccination center details
        VaccinationCenter center = centerRepo.findById(id).get();
        requiredResponse.setCenter(center);

        //Get all citizens  registered on that center
        //Connect to the Citizen service to get all citizens

        List<Citizen> citizenList = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/" + id, List.class);
        requiredResponse.setCitizenList(citizenList);
        return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);

    }

    public ResponseEntity<RequiredResponse> fallbackMethodWhenCitizenServiceIsDown(@PathVariable Integer id) {

        RequiredResponse requiredResponse = new RequiredResponse();
        //Get vaccination center details
        VaccinationCenter center = centerRepo.findById(id).get();
        requiredResponse.setCenter(center);
        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);

    }
}
