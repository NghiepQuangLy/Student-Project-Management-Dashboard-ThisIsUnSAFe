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
public class RemoveTrelloRequest {

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String trelloId;

    @NotBlank
    private String projectId;
}
