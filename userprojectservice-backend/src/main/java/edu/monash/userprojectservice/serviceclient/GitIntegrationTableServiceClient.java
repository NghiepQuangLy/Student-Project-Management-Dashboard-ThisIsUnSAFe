package edu.monash.userprojectservice.serviceclient;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GitIntegrationTableServiceClient {

    private static final String GIT_INTEGRATION_URL = "http://localhost:5000/user-project-service/test-array-request-param?emails={id}&git-ids={ids}";
    private RestTemplate restTemplate;

    public List<IntegrationTableResponse> getGitIntegrationTable(List<String> emails, List<String> gitIds) {

        try {
            String emailsString = emails.toString();
            emailsString = emailsString.substring(1, emailsString.length() - 1);

            String gitIdsString =gitIds.toString();
            gitIdsString = gitIdsString.substring(1, gitIdsString.length() - 1);

            return restTemplate.exchange(
                    GIT_INTEGRATION_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IntegrationTableResponse>>() {
                    },
                    emailsString,
                    gitIdsString
            ).getBody();

        } catch (Exception e) {
            return new ArrayList();
        }
    }
}
