package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CheckAdminResponse;
import edu.monash.userprojectservice.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class AdminController {

    private AdminService adminService;

    @ResponseStatus(OK)
    @GetMapping("/check-admin")
    public CheckAdminResponse checkAdmin(@RequestParam("email") String emailAddress) {
        return adminService.checkAdminByEmail(emailAddress);
    }
}
