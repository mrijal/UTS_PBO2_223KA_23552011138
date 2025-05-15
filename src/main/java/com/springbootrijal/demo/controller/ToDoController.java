package com.springbootrijal.demo.controller;

import com.springbootrijal.demo.model.ToDo;
import com.springbootrijal.demo.service.ToDoService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.springbootrijal.demo.model.User;
import com.springbootrijal.demo.repository.UserRepository;
import com.springbootrijal.demo.repository.ToDoRepository;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository todoRepository;

    @GetMapping("/")
    public String showIndex(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("todos", toDoService.getAllTodos());
        model.addAttribute("todo", new ToDo());
        return "index";
    }

    @GetMapping("/todos")
    public String showTodos(HttpServletRequest request, Model model, Principal principal) {
        model.addAttribute("currentUri", request.getRequestURI());

        // Ambil user yang sedang login
        String username = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if (user == null) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
        model.addAttribute("todos", toDoService.getTodosByUser(user));
        model.addAttribute("todo", new ToDo());
        return "todos";
    }

    @GetMapping("/todos/create")
    public String createTodos(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("todo", new ToDo());
        return "add-todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo, Model model, Principal principal) {
        try {
            // Validasi manual
            if (todo.getTask() == null || todo.getTask().trim().isEmpty()) {
                throw new IllegalArgumentException("Task tidak boleh kosong");
            }

            if (todo.getDate() == null || todo.getDate().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Tanggal harus di masa depan");
            }

            // Ambil user yang sedang login
            String username = principal.getName();
            Optional<User> optionalUser = userRepository.findByUsername(username);
            User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
            if (user == null) {
                throw new IllegalArgumentException("User tidak ditemukan");
            }

            // Set user ke todo
            todo.setUser(user);

            // Simpan todo
            toDoService.saveTodo(todo);

            return "redirect:/todos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("todo", todo);

            // Tampilkan hanya todo milik user yang sedang login
            String username = principal.getName();
            Optional<User> optionalUser = userRepository.findByUsername(username);
            User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
            List<ToDo> userTodos = toDoService.getTodosByUser(user);
            model.addAttribute("todos", userTodos);

            return "add-todo";
        }
    }


    @PostMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @GetMapping("/todos/edit/{id}")
    public String editTodos(@PathVariable Long id, HttpServletRequest request, Model model) {
        ToDo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo ID: " + id));
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("todo", todo);
        return "edit-todo";
    }

    @PostMapping("/todos/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        Optional<ToDo> todo = toDoService.getTodoById(id);
        todo.ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            toDoService.saveTodo(t);
        });
        return "redirect:/todos";
    }

    @PostMapping("/todos/update/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute ToDo todo) {
        // Validasi manual
        if (todo.getTask() == null || todo.getTask().trim().isEmpty()) {
            throw new IllegalArgumentException("Task tidak boleh kosong");
        }
        if (todo.getDate() == null || todo.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Tanggal harus di masa depan");
        }
        // Ambil todo berdasarkan ID
        Optional<ToDo> existingTodo = toDoService.getTodoById(id);
        if (existingTodo.isPresent()) {
            ToDo updatedTodo = existingTodo.get();
            updatedTodo.setTask(todo.getTask());
            updatedTodo.setDate(todo.getDate());
            updatedTodo.setCompleted(todo.isCompleted());
            toDoService.saveTodo(updatedTodo);
        } else {
            throw new IllegalArgumentException("Todo tidak ditemukan");
        }
        return "redirect:/todos";
    }
}
