package com.springbootrijal.demo.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String task;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private boolean completed;

    public ToDo() {
    }

    public ToDo(String task, LocalDateTime date, boolean completed) {
        if (task == null || task.trim().isEmpty()) {
            throw new IllegalArgumentException("Task tidak boleh kosong");
        }

        if (date == null || date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Tanggal harus di masa depan");
        }

        this.task = task;
        this.date = date;
        this.completed = completed;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}