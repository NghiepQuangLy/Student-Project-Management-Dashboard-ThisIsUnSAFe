package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveGoogleDriveRequest {

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String googleDriveId;

    @NotBlank
    private String projectId;

    @NotBlank
    private String googleDriveName;
}
