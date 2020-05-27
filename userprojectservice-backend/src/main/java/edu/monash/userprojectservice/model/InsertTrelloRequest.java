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
public class InsertTrelloRequest {

    @NotNull
    private int trelloId;

    @NotNull
    private int projectId;
}
