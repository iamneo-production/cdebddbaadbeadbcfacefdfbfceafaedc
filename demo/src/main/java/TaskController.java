import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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