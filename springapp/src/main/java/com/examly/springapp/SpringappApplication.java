package com.examly.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappApplication.class, args);
	}

}
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "task") // Name of the table
class Task {
    @Id // This tells Hibernate to make this field the primary key
    @GeneratedValue(strategy=GenerationType.AUTO) // This tells Hibernate to auto-increment the primary key
    private Integer id;
    private String taskId;
    private String taskHolderName;
    private String taskDate;
    private String taskName;
    private String taskStatus;
    // Getters and setters omitted for brevity
}
@RestController
@RequestMapping("/task")
class TaskController {
    @Autowired // This means to get the bean called taskRepository
    private TaskRepository taskRepository;
    @PostMapping("/saveTask")
    public void saveTask(@RequestBody Task task) {
        taskRepository.save(task);
    }
    @GetMapping("/changeStatus")
    public void changeStatus(@RequestParam Integer id, @RequestParam String taskStatus) {
        Task task = taskRepository.findById(id).get();
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
    }
    @GetMapping("/deleteTask")
    public void deleteTask(@RequestParam Integer id) {
        taskRepository.deleteById(id);
    }
    @GetMapping("/alltasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    @GetMapping("/getTask")
    public Task getTask(@RequestParam Integer id) {
        return taskRepository.findById(id).get();
    }
}
interface TaskRepository extends JpaRepository<Task, Integer> {}
