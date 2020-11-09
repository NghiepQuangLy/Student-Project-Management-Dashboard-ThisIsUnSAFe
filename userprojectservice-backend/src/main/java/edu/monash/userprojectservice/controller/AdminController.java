package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.model.CheckAdminResponse;
import edu.monash.userprojectservice.service.AdminService;
import edu.monash.userprojectservice.serviceclient.GitIntegrationTableServiceClient;
import edu.monash.userprojectservice.serviceclient.IntegrationTableResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class AdminController {

    private AdminService adminService;

    private GitIntegrationTableServiceClient gitIntegrationTableServiceClient;
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



    @ResponseStatus(OK)
    @GetMapping("/test-array-request-param")
    public List<IntegrationTableResponse> testArrayRequestParam(
            @RequestParam(value = "git-ids", required = false) List<String> git,
            @RequestParam(value = "trello-ids", required = false) List<String> trello,
            @RequestParam(value = "drive-ids", required = false) List<String> drive,
            @RequestParam("emails") List<String> emails
    ) {
        LocalDateTime newdate = LocalDateTime.now();

        List<IntegrationTableResponse> t = emails.stream().map(email -> {
            Random rand = new Random();
            int n = rand.nextInt(50);

            return IntegrationTableResponse.builder()
                    .email(email)
                    .lastModified(newdate.minusDays(n))
                    .build();
        }).collect(Collectors.toList());

        return t;
    }

}
