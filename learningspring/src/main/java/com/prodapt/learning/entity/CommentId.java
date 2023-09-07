package com.prodapt.learning.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Embeddable
@Data
public class CommentId implements Serializable{
	private static final long serialVersionUID = 5469065220719817005L;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "user_id", referencedColumnName = "id")
	  private User user;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "post_id", referencedColumnName = "id")
	  private Post post;

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

}










































