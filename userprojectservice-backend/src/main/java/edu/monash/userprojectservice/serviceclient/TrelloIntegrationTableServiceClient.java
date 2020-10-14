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
public class TrelloIntegrationTableServiceClient {

    private static final String TRELLO_INTEGRATION_URL = "http://localhost:5000/user-project-service/test-array-request-param?emails={id}&trello-ids={ids}";
    private RestTemplate restTemplate;

    public List<IntegrationTableResponse> getTrelloIntegrationTable(List<String> emails, List<String> trelloIds) {

        try {
            String emailsString = emails.toString();
            emailsString = emailsString.substring(1, emailsString.length() - 1);

            String trelloIdsString =trelloIds.toString();
            trelloIdsString = trelloIdsString.substring(1, trelloIdsString.length() - 1);

            return restTemplate.exchange(
                    TRELLO_INTEGRATION_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IntegrationTableResponse>>() {
                    },
                    emailsString,
                    trelloIdsString
            ).getBody();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
