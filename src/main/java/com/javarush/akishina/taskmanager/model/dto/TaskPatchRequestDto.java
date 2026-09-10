package com.javarush.akishina.taskmanager.model.dto;

import com.javarush.akishina.taskmanager.model.type.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskPatchRequestDto {

    private String title;
    private String description;
    private TaskStatus taskStatus;
    private Instant deadline;
    private Long ownerId;

}
