package com.spring.blog.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="blogs_table")
public class Blog {
	@Id
	@Column(name="blog_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int blogId;
	@Column(name="blog_title", nullable=false)
	private String blogTitle;
	@Column(name="blog_content", nullable=false, columnDefinition="TEXT")
	private String blogContent;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
	@JsonIgnoreProperties({"blogs", "password"})
	private User user;
	
	
	
}
