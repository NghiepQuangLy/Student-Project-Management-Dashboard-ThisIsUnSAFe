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
public class RemoveGoogleDriveRequest {

    @NotNull
    private String emailAddress;

    @NotNull
    private String googleDriveId;

    @NotNull
    private String projectId;
}
