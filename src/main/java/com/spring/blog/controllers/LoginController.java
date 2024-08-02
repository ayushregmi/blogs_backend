package com.spring.blog.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.entities.User;
import com.spring.blog.service.UserService;
import com.spring.blogs.dto.LoginDto;
import com.spring.blogs.dto.UserDto;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
		
		String username = loginDto.getUserName();
		
		
		System.out.println(LocalDateTime.now());
		
		User u = this.userService.getUserByUsername(username);
		
		if(u == null) {
			return new ResponseEntity<String>("Invalid username", HttpStatus.NOT_FOUND);
		}
		else {
			String password = u.getPassword();
			
			if(password.equals(loginDto.getPassword())) {
				return new ResponseEntity<UserDto>(new UserDto(u.getUserId(), u.getFullName(), u.getUserName(), u.getBlogs()), HttpStatus.OK);
			}
			else {
				
				return new ResponseEntity<String>("Invalid Password", HttpStatus.UNAUTHORIZED);
			}
			
		}
		
	}
	
}
