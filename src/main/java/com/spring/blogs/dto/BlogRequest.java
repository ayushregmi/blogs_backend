package com.spring.blogs.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogRequest {
	private String blogTitle;
	private String blogContent;
	private String userName;
}
