package com.prodapt.learning;
import com.prodapt.learning.*;
import lombok.Data;

@Data
public class AddPostForm {
  private String content;
  private int userId;
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
}