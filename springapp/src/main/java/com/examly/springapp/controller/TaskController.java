package com.examly.springapp.controller;

import java.util.List;

import com.examly.springapp.model.Task;
import com.examly.springapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TaskController {
    @Autowired
    public TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<Task> changeStatus(@RequestParam Long id, @RequestParam String status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setTaskStatus(status);
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<Void> deleteTask(@RequestParam Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/getTask")
    public ResponseEntity<Task> getTaskByHolderName(@RequestParam String taskHolderName) {
        Task task = taskRepository.findByTaskHolderName(taskHolderName);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
}