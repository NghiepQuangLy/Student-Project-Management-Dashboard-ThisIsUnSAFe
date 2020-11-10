package edu.monash.userprojectservice.serviceclient;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GoogleDriveIntegrationTableServiceClient {

    private static final String GOOGLE_DRIVE_INTEGRATION_URL = "http://localhost:5000/user-project-service/test-array-request-param?emails={id}&drive-ids={ids}";
    private RestTemplate restTemplate;

    public List<IntegrationTableResponse> getGoogleDriveIntegrationTable(List<String> emails, List<String> driveIds) {

        try {
            String emailsString = emails.toString();
            emailsString = emailsString.substring(1, emailsString.length() - 1);

            String driveIdsString =driveIds.toString();
            driveIdsString = driveIdsString.substring(1, driveIdsString.length() - 1);

            return restTemplate.exchange(
                    GOOGLE_DRIVE_INTEGRATION_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IntegrationTableResponse>>() {
                    },
                    emailsString,
                    driveIdsString
            ).getBody();

        } catch (Exception e) {
            System.out.println("Issue: " + e);
            return new ArrayList<>();
        }
    }
}
