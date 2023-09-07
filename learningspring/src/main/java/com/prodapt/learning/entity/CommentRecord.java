package com.prodapt.learning.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class CommentRecord {

	  @Column(unique=true)
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  
	  @EmbeddedId
	  private CommentId commentid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CommentId getCommentid() {
		return commentid;
	}

	public void setCommentid(CommentId commentid) {
		this.commentid = commentid;
	}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
