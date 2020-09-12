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
public class CreateProjectRequest {

    @NotNull
    private List<String> emailAddress;

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectUnit;

    @NotBlank
    private String projectYear;

    @NotBlank
    private String projectSemester;

}
