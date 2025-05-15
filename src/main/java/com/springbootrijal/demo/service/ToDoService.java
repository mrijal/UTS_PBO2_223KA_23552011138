package com.springbootrijal.demo.service;

import com.springbootrijal.demo.model.ToDo;
import com.springbootrijal.demo.repository.ToDoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.springbootrijal.demo.model.User;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public void saveTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }

    public Optional<ToDo> getTodoById(Long id) {
        return toDoRepository.findById(id);
    }

    public List<ToDo> getTodosByUser(User user) {
        return toDoRepository.findByUser(user);
    }
}