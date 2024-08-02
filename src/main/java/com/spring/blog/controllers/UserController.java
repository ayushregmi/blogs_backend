package com.spring.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.blog.entities.Blog;
import com.spring.blog.entities.User;
import com.spring.blog.service.UserService;
import com.spring.blogs.dto.UserDto;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllUsers() {

		List<UserDto> users = userService.getAllUsers().stream()
				.map(user -> new UserDto(user.getUserId(), user.getFullName(), user.getUserName(), user.getBlogs()))
				.collect(Collectors.toList());
//		List<User> users = userService.getAllUsers();
		
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/{userName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
		User user = this.userService.getUserByUsername(userName);

		if (user != null) {
			return new ResponseEntity<UserDto>(new UserDto(user.getUserId(), user.getFullName(), user.getUserName(), user.getBlogs()), HttpStatus.OK);
//			return new ResponseEntity<User> (user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		String username = user.getUserName();

		if (this.userService.getUserByUsername(username) != null) {
			return new ResponseEntity<String>("Username already exists", HttpStatus.CONFLICT);
		}

		user.setBlogs(new ArrayList<Blog>());
		
		System.out.println(1);
		
		User u = this.userService.createUser(user);

		System.out.println(2);
		
		return new ResponseEntity<UserDto>(new UserDto(u.getUserId(), u.getFullName(), u.getUserName(), u.getBlogs()), HttpStatus.OK);
	}
}
