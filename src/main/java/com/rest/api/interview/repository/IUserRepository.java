package com.rest.api.interview.repository;

import java.util.Optional;

import com.rest.api.interview.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IUserReposotory
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
}