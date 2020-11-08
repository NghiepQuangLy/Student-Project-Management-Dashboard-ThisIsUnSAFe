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
public class ReminderTableServiceClient {

    private static final String REMINDER_TABLE_URL = "http://localhost:5000/spmd_reminders/sorted/{projectId}";
    private RestTemplate restTemplate;

    public List<ReminderTableResponse> getReminderTable(String projectId) {

        try {

            return restTemplate.exchange(
                    REMINDER_TABLE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ReminderTableResponse>>() {
                    },
                    projectId
            ).getBody();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
