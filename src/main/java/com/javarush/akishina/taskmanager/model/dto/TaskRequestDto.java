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
public class TaskRequestDto {

    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title must not be longer then {max} characters")
    private String title;
    private String description;
    private Instant deadline;
    private Long ownerId;

}