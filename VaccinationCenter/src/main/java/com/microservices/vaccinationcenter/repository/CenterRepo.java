package com.microservices.vaccinationcenter.repository;

import com.microservices.vaccinationcenter.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepo extends JpaRepository<VaccinationCenter,Integer> {
}
