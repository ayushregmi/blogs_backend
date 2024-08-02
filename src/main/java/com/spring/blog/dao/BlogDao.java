package com.spring.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.blog.entities.Blog;

@Repository
public interface BlogDao extends JpaRepository<Blog, Integer> {
}
