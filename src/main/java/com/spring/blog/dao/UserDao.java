package com.spring.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.blog.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	public User findByUserName(String name);
}
