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
public class SaveGitRequest {

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String gitId;

    @NotBlank
    private String projectId;

    @NotBlank
    private String gitName;
}
