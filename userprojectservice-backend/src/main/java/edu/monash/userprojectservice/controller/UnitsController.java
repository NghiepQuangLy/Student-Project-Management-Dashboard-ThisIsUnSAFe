package edu.monash.userprojectservice.controller;


import edu.monash.userprojectservice.model.GetUnitsResponse;
import edu.monash.userprojectservice.service.UnitsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class UnitsController {

    private UnitsService unitsService;

    @ResponseStatus(OK)
    @GetMapping("/get-units")
    public GetUnitsResponse getUnits(@RequestParam("requestorEmail") String emailAddress) {
        return unitsService.getUnits(emailAddress);
    }
}
