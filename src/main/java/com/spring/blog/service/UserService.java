package com.spring.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.dao.UserDao;
import com.spring.blog.entities.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	public User getUserById(int id) {
		
		Optional<User> user = userDao.findById(id);
		
		if(user.isEmpty()) {
			return null;
		}
		
		return user.get();
	}
	
	public User getUserByUsername(String userName) {
		return userDao.findByUserName(userName);
	}
	
	public User createUser(User user) {
		return userDao.save(user);
	}
}
