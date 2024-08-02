package com.spring.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.dao.BlogDao;
import com.spring.blog.entities.Blog;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	public List<Blog> getAllBlogs(){
		return this.blogDao.findAll();
	}
	
	public Blog getBlogById(int blogId) {
		
		Optional<Blog> blog = this.blogDao.findById(blogId);
		
		if(blog.isEmpty()) {
			return null;
		}
		
		return blog.get();
	}
	
	public Blog createBlog(Blog blog) {
		blog.setCreatedAt(LocalDateTime.now());
		return this.blogDao.save(blog);
	}
	
	public void updateBlog(Blog blog) {
		this.blogDao.save(blog);
	}
	
}
