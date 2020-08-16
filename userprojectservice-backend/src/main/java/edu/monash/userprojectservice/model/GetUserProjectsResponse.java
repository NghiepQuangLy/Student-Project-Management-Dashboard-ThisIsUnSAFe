package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProjectsResponse {
    private List<GetUserResponse> users;
    private ProjectListResponse projects;
}
