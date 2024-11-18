package com.provapoo.kanban_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Bem-vindo à API Kanban! Utilize /api/tasks para gerenciar suas tarefas.";
    }
}
