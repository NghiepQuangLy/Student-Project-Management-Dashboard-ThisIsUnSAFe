package edu.monash.userprojectservice.service;


import edu.monash.userprojectservice.ValidationHandler;
import edu.monash.userprojectservice.model.GetUnitsResponse;
import edu.monash.userprojectservice.repository.units.UnitEntity;
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

    @Autowired
    private ValidationHandler validationHandler;

    /*
     * This method is to get all units
     * @param emailAddress The email address to be validated
     * @return GetUnitsResponse This returns all units' details
     * @exception BadRequestException when email is empty
     * @exception ForbiddenException when requestor is not admin
     */
    public GetUnitsResponse getUnits(String emailAddress) {

        validationHandler.isUserAdmin(emailAddress);

        log.info("{\"message\":\"Getting Units \"}");
        // get from database
        List<UnitEntity> unitsEntities = unitsRepository.findAll();

        log.info("{\"message\":\"Got all Units \"}");
        return new GetUnitsResponse(unitsEntities);
    }
}

