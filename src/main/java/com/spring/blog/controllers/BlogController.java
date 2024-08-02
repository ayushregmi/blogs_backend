package com.spring.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.blog.entities.Blog;
import com.spring.blog.entities.User;
import com.spring.blog.service.BlogService;
import com.spring.blog.service.UserService;
import com.spring.blogs.dto.BlogRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/api/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Blog>> getAllBlogs(){
		return new ResponseEntity<List<Blog>>(this.blogService.getAllBlogs(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{blogId}")
	public ResponseEntity<?> getBlogById(@PathVariable int blogId){
		Blog blog = this.blogService.getBlogById(blogId);
		
		if(blog != null) {
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Blog not found", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createBlog(@RequestBody BlogRequest blogRequest){
		
		String userName = blogRequest.getUserName();
		
		User user = this.userService.getUserByUsername(userName);
		
		if(user == null) {
			return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
		}
		
		Blog blog = new Blog();
		blog.setBlogTitle(blogRequest.getBlogTitle());
		blog.setBlogContent(blogRequest.getBlogContent());
		blog.setUser(user);
		
		this.blogService.createBlog(blog);
		
		return new ResponseEntity<String>("Blog added", HttpStatus.OK);
	}
	
	@PutMapping("/update/{blogId}")
	public ResponseEntity<String> updateBlog(@RequestBody BlogRequest blogRequest, @PathVariable int blogId){
		
		Blog blog = this.blogService.getBlogById(blogId);
		
		if(blog == null) {
			return new ResponseEntity<String>("Blog doesn't exist", HttpStatus.NOT_FOUND);
		}
		
		blog.setBlogContent(blogRequest.getBlogContent());
		blog.setBlogTitle(blogRequest.getBlogTitle());
		
		this.blogService.updateBlog(blog);
		
		return new ResponseEntity<String> ("Blog Updated", HttpStatus.OK);
	}
	
}
