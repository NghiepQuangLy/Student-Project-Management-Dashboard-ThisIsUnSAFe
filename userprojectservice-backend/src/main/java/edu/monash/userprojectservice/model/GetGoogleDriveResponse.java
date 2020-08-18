package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.repository.googleDrive.GoogleDriveEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGoogleDriveResponse {
    private List<GoogleDriveEntity> googleDriveId;
}
