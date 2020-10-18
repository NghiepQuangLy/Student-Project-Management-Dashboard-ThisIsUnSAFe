package edu.monash.userprojectservice.serviceclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReminderTableResponse {

    private String reminderActivity;
    private String reminderUnitCode;
    private String reminderUnitName;
    private String reminderDate;
    private String reminderTime;

}
