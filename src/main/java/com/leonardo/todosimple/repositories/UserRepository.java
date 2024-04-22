package com.leonardo.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.todosimple.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}