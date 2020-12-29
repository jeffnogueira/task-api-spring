package com.task.taskapi.repository;

import org.springframework.stereotype.Repository;

import com.task.taskapi.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
