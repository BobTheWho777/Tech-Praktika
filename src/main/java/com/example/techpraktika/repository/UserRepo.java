package com.example.techpraktika.repository;


import com.example.techpraktika.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select u from User u where lower(u.username) like concat('%', lower(:search), '%')")
    List<User> findByName(@Param("search") String search);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}