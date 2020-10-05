package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListResponse {
    private String projectId;
    private String projectName;
    private String projectUnitCode;
    private Integer projectYear;
    private String projectSemester;
}
