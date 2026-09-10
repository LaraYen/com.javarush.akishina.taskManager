package com.javarush.akishina.taskmanager.controller;

import com.javarush.akishina.taskmanager.model.dto.TaskPatchRequestDto;
import com.javarush.akishina.taskmanager.model.dto.TaskRequestDto;
import com.javarush.akishina.taskmanager.model.dto.TaskResponseDto;
import com.javarush.akishina.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(
            @PathVariable Long id
    ) {
        TaskResponseDto taskResponseDto = taskService.findById(id);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDto> createTask(
            @Valid @RequestBody TaskRequestDto taskRequestDto
            ) {

        TaskResponseDto createdTask = taskService.createTask(taskRequestDto);
        return ResponseEntity.ok(createdTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskPatchRequestDto taskPatchRequestDto
    ) {

        TaskResponseDto updatedTask = taskService.updateTask(id, taskPatchRequestDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long id
    ) {

        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
