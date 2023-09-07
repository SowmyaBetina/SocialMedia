package com.prodapt.learning.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
public class Comment {
    public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
  
  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  	@Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    private String content;
    

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

 

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
	