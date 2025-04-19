package com.springbootrijal.demo.controller;

import com.springbootrijal.demo.model.ToDo;
import com.springbootrijal.demo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("todos", toDoService.getAllTodos());
        model.addAttribute("todo", new ToDo()); // 👈 Required for th:field
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo, Model model) {
        try {
            // Manual validation
            if (todo.getTask() == null || todo.getTask().trim().isEmpty()) {
                throw new IllegalArgumentException("Task tidak boleh kosong");
            }

            if (todo.getDate() == null || todo.getDate().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Tanggal harus di masa depan");
            }

            toDoService.saveTodo(todo);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("todo", todo);
            model.addAttribute("todos", toDoService.getAllTodos());
            return "index";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id) {
        Optional<ToDo> todo = toDoService.getTodoById(id);
        todo.ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            toDoService.saveTodo(t);
        });
        return "redirect:/";
    }
}
