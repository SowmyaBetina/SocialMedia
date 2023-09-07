package com.prodapt.learning.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Post {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  
  private String content;
  
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private User author;

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;

	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public User getAuthor() {
	return author;
}

public void setAuthor(User author) {
	this.author = author;
}
}