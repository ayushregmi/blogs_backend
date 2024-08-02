package com.spring.blogs.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	private String userName;
	private String password;
}
