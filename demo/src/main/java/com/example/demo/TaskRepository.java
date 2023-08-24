package com.example.demo;
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Define custom queries if needed
}
