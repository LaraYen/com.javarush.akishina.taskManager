package com.javarush.akishina.taskmanager.service;

import com.javarush.akishina.taskmanager.model.dto.TaskPatchRequestDto;
import com.javarush.akishina.taskmanager.model.dto.TaskRequestDto;
import com.javarush.akishina.taskmanager.model.dto.TaskResponseDto;
import com.javarush.akishina.taskmanager.model.type.TaskStatus;
import com.javarush.akishina.taskmanager.repository.TaskRepository;
import com.javarush.akishina.taskmanager.repository.UserRepository;
import com.javarush.akishina.taskmanager.repository.entity.TaskEntity;
import com.javarush.akishina.taskmanager.repository.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskResponseDto> findAll() {

        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        taskRepository.findAll().forEach(t -> taskResponseDtoList.add(mapEntityToResponseDto(t)));

        return taskResponseDtoList;
    }

    public TaskResponseDto findById(Long id) {

        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        return mapEntityToResponseDto(taskEntity);

    }

    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {

        TaskEntity newTaskEntity = taskRepository.save(
                TaskEntity.builder()
                        .title(taskRequestDto.getTitle())
                        .deadline(taskRequestDto.getDeadline())
                        .taskStatus(TaskStatus.NEW)
                        .description(taskRequestDto.getDescription())
                        .ownerUser(getUserByOwnerId(taskRequestDto.getOwnerId()))
                        .build()
        );

        return mapEntityToResponseDto(newTaskEntity);
    }

    public TaskResponseDto updateTask(Long taskId, TaskPatchRequestDto taskPatchRequestDto) {

        TaskEntity taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        if (taskPatchRequestDto.getTitle() != null) {
            taskToUpdate.setTitle(taskPatchRequestDto.getTitle());
        }
        if (taskPatchRequestDto.getDescription() != null) {
            taskToUpdate.setDescription(taskPatchRequestDto.getDescription());
        }
        if (taskPatchRequestDto.getTaskStatus() != null) {
            taskToUpdate.setTaskStatus(taskPatchRequestDto.getTaskStatus());
        }
        if (taskPatchRequestDto.getDeadline() != null) {
            taskToUpdate.setDeadline(taskPatchRequestDto.getDeadline());
        }
        if (taskPatchRequestDto.getOwnerId() != null) {
            taskToUpdate.setOwnerUser(getUserByOwnerId(taskPatchRequestDto.getOwnerId()));
        }

        return mapEntityToResponseDto(taskRepository.save(taskToUpdate));
    }

    public void deleteTask(Long taskId) {

        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        taskEntity.setTaskStatus(TaskStatus.DELETED);
        taskRepository.save(taskEntity);

    }


    private UserEntity getUserByOwnerId(Long ownerId) {

        if (ownerId != null) {
            return userRepository.findById(ownerId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id " + ownerId));
        }

        return null;
    }

    private Long getOwnerIdByUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return userEntity.getId();
    }

    private TaskResponseDto mapEntityToResponseDto(TaskEntity taskEntity) {

        return TaskResponseDto.builder()
                .id(taskEntity.getId())
                .ownerId(getOwnerIdByUser(taskEntity.getOwnerUser()))
                .taskStatus(taskEntity.getTaskStatus())
                .deadline(taskEntity.getDeadline())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .build();
    }
}
