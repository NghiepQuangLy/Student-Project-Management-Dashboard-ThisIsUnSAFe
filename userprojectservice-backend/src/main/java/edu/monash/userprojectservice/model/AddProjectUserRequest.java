package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AddProjectUserRequest {

    @NotBlank
    private String requestorEmail;

    @NotBlank
    private String projectId;

    @NotNull
    private List<String> emailAddress;
}
