package com.provapoo.kanban_api.service;

import com.provapoo.kanban_api.model.Task;
import com.provapoo.kanban_api.model.Status;
import com.provapoo.kanban_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Método para criar uma nova tarefa
    public Task createTask(Task task) {
        // A tarefa deve ser criada com status "TO_DO" por padrão
        task.setStatus(Status.TO_DO);
        return taskRepository.save(task);
    }

    // Método para obter todas as tarefas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Método para obter uma tarefa pelo ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Método para atualizar uma tarefa
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        // Atualizando as propriedades da tarefa
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority()); // Atualizando prioridade
        task.setDueDate(taskDetails.getDueDate()); // Atualizando data limite
        return taskRepository.save(task);
    }

    // Método para deletar uma tarefa
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

    // Método para mover a tarefa para a próxima coluna
    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        // Movendo a tarefa para a próxima coluna
        switch (task.getStatus()) {
            case TO_DO:
                task.setStatus(Status.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                task.setStatus(Status.DONE);
                break;
            case DONE:
                // Se já estiver concluída, não faz mais sentido mover.
                throw new RuntimeException("Task is already completed.");
        }
        return taskRepository.save(task);
    }
}
