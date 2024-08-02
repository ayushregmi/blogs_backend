package com.spring.blogs.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.blog.entities.Blog;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
	private int userId;
	private String fullName;
	private String userName;
	
	@JsonIgnoreProperties({"user"})
	private List<Blog> blogs;
}
