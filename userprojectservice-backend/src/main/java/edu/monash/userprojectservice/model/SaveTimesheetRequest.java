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
public class SaveTimesheetRequest {

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String timesheet;

    @NotBlank
    private String projectId;
}
