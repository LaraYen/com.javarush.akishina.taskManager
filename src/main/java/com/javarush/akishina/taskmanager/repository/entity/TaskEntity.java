package com.javarush.akishina.taskmanager.repository.entity;

import com.javarush.akishina.taskmanager.model.type.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false, length = 20)
    private TaskStatus taskStatus;

    @Column(name = "deadline")
    private Instant deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity ownerUser;

}
