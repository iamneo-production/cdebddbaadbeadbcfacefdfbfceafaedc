package com.examly.springapp.controller;

import java.util.List;

import com.examly.springapp.model.Task;
import com.examly.springapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/saveTask")
    public Task saveTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/changeStatus")
    public void changeStatus(@RequestParam Long id, @RequestParam String status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskStatus(status);
        taskRepository.save(task);
    }

    @GetMapping("/deleteTask")
    public void deleteTask(@RequestParam Long id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/alltasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/getTask")
    public Task getTask(@RequestParam Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}
