package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProjectRequest {

    @NotBlank
    private String requestorEmail;

    @NotBlank
    private String projectId;

    @NotBlank
    private String newProjectName;

}
