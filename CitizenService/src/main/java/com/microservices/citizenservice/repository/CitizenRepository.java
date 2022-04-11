package com.microservices.citizenservice.repository;

import com.microservices.citizenservice.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    List<Citizen> findByVaccinationCenterId(int id);
}
