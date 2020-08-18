package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.repository.git.GitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGitResponse {
    private List<GitEntity> gitId;
}
