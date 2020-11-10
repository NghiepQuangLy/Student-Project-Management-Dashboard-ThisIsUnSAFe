package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveGoogleDriveRequest {

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String googleDriveId;

    @NotBlank
    private String projectId;
}
