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
public class GitIntegrationTableServiceClient {

    private static final String GIT_INTEGRATION_URL = "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/git/project/repository/last-changed-email?emails={id}&git-ids={ids}&project-id={projectId}";
    private RestTemplate restTemplate;

    public List<IntegrationTableResponse> getGitIntegrationTable(List<String> emails, List<String> gitIds, String projectId) {

        try {
            String emailsString = emails.toString();
            emailsString = emailsString.substring(1, emailsString.length() - 1);

            String gitIdsString =gitIds.toString();
            gitIdsString = gitIdsString.substring(1, gitIdsString.length() - 1);

            System.out.println(gitIdsString);
            System.out.println(emailsString);
             System.out.println("TEST: " + restTemplate.exchange(
                    GIT_INTEGRATION_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IntegrationTableResponse>>() {
                    },
                    emailsString,
                    gitIdsString,
                    projectId
            ).getBody());

            return restTemplate.exchange(
                    GIT_INTEGRATION_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<IntegrationTableResponse>>() {
                    },
                    emailsString,
                    gitIdsString,
                    projectId
            ).getBody();

        } catch (Exception e) {
            System.out.println("Issue: " + e);
            return new ArrayList<>();
        }
    }
}
