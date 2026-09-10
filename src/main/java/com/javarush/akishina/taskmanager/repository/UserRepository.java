package com.javarush.akishina.taskmanager.repository;

import com.javarush.akishina.taskmanager.repository.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
