package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationTableObjectResponse {

    private String emailAddress;
    private String gitIntegrationLastModified;
    private String googleDriveIntegrationLastModified;
    private String trelloIntegrationLastModified;
}
