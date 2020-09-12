package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveGitRequest {

    @NotNull
    private String emailAddress;

    @NotNull
    private String gitId;

    @NotNull
    private String projectId;

    @NotNull
    private String gitName;
}
