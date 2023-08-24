package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model;
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Define custom queries if needed
}
