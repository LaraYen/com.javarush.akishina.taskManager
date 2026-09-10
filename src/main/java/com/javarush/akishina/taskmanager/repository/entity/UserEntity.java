package com.javarush.akishina.taskmanager.repository.entity;

import com.javarush.akishina.taskmanager.model.type.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "user_status", nullable = false, length = 20)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "ownerUser")
    private List<TaskEntity> tasks;

}

