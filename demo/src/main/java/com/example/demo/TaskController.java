package com.example.demo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import com.example.demo;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/saveTask")
    public ResponseEntity<String> saveTask(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok("Task saved successfully");
    }

    @GetMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam Long id, @RequestParam String taskStatus) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTaskStatus(taskStatus);
            taskRepository.save(task);
            return ResponseEntity.ok("Task status changed successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/getTask")
    public ResponseEntity<Task> getTaskById(@RequestParam Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}