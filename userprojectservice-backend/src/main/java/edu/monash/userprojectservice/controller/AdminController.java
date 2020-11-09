package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CheckAdminResponse;
import edu.monash.userprojectservice.service.AdminService;
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
public class AdminController {

    private AdminService adminService;

    /*
     * This method is used to check if a given email is admin
     * @requestParam emailAddress The email address to be validated
     * @return 200 CheckAdminResponse isAdmin = true when the email is admin, else isAdmin = false
     * @return 400 when email is empty
     */
    @ResponseStatus(OK)
    @GetMapping("/check-admin")
    public CheckAdminResponse checkAdmin(@RequestParam("email") String emailAddress) {
        return adminService.checkAdminByEmail(emailAddress);
    }
}
