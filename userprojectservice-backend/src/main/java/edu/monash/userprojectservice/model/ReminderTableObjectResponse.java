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

    private String reminderActivity;
    private String reminderUnitCode;
    private String reminderUnitName;
    private String reminderDate;
    private String reminderTime;
}
