package com.provapoo.kanban_api.controller;

import com.provapoo.kanban_api.model.Task;
import com.provapoo.kanban_api.model.Status; // Certifique-se de importar o enum Status
import com.provapoo.kanban_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}/status")
    public Task updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        // Converta a string recebida para o enum Status
        try {
            task.setStatus(Status.valueOf(status.toUpperCase()).name());  // Corrigido aqui
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value. Valid values: TO_DO, IN_PROGRESS, DONE.");
        }

        return taskService.updateTask(id, task);
    }


    @PutMapping("/{id}/status")
    public Task updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        // Converta o valor recebido para o enum Status
        try {
            task.setStatus(Status.valueOf(status.toUpperCase()).name());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value. Valid values: TO_DO, IN_PROGRESS, DONE.");
        }

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
