package edu.monash.userprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReminderTableObjectResponse {

    private String reminderName;
    private String reminderProject;
    private String reminderDueDate;
    private String reminderDesc;
}
