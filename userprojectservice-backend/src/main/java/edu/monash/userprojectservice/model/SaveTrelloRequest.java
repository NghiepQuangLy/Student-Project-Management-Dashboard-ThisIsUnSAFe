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
public class SaveTrelloRequest {

    @NotNull
    private String emailAddress;

    @NotNull
    private String trelloId;

    @NotNull
    private String projectId;

    @NotNull
    private String trelloName;
}
