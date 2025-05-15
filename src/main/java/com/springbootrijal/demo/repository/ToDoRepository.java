package com.springbootrijal.demo.repository;

import java.util.List;

import com.springbootrijal.demo.model.ToDo;
import com.springbootrijal.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUser(User user);
}
