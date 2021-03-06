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
public class RemoveProjectRequest {

    @NotBlank
    private String requestorEmail;

    @NotBlank
    private String projectId;
}
