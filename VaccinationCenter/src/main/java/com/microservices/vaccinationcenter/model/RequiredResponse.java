package com.microservices.vaccinationcenter.model;

import com.microservices.vaccinationcenter.entity.VaccinationCenter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {

    private VaccinationCenter center;
    private List<Citizen> citizenList;
}
