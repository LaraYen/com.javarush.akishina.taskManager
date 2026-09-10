package com.javarush.akishina.taskmanager.repository;

import com.javarush.akishina.taskmanager.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
