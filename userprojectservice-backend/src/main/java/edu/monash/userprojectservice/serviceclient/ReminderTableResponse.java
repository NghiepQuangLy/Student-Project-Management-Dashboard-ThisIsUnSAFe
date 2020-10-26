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

    private String name;
    private String project;
    private String dueDate;
    private String desc;

}
