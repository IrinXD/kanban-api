package com.provapoo.kanban_api.repository;

import com.provapoo.kanban_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Aqui você pode adicionar métodos customizados, se necessário
}