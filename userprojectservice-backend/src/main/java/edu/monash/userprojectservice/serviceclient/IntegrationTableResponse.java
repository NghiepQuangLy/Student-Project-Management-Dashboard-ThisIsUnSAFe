package edu.monash.userprojectservice.serviceclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationTableResponse {

    private String email;
    private Optional<LocalDateTime> lastModified;

}
