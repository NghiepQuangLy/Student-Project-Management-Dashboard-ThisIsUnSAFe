package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectResponse {
    private Integer projectId;
    private String projectName;
    //private ArrayList<Integer> projectGitIds;
    //private ArrayList<Integer> projectTrelloIds;
    //private ArrayList<Integer> projectGoogleDocIds;
}
