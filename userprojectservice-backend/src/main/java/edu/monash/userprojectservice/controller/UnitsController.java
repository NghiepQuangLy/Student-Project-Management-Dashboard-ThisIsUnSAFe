package edu.monash.userprojectservice.controller;


import edu.monash.userprojectservice.model.GetUnitsResponse;
import edu.monash.userprojectservice.service.UnitsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-admin-frontend.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com",
        "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/",
        "http://localhost:3002",
        "http://ancient-springs-72265.herokuapp.com",
        "http://ancient-springs-72265.herokuapp.com/",
        "http://localhost:3003",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com/",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com",
        "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com/",
        "http://d21emc0xlr7tto.cloudfront.net"
}, maxAge = 0)
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