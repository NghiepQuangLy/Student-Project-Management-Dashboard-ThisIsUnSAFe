package edu.monash.userprojectservice.service;


import edu.monash.userprojectservice.model.GetUnitsResponse;
import edu.monash.userprojectservice.repository.units.UnitsEntity;
import edu.monash.userprojectservice.repository.units.UnitsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UnitsService {

    @Autowired
    private UnitsRepository unitsRepository;


    // Get from Units table
    public GetUnitsResponse getUnits() {
        log.info("{\"message\":\"Getting Units \"}");

        // get from database
        List<UnitsEntity> unitsEntities = unitsRepository.findAll();

        log.info("{\"message\":\"Got all Units \"}");
        return new GetUnitsResponse(unitsEntities);
    }


}

