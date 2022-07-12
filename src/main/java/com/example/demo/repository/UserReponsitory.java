package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReponsitory extends JpaRepository<User, Integer> {
    @Query(value = "SELECT e FROM User e")
    Page<User> findUser(Pageable pageable);
    List<User>findAll(Sort sort);

}
