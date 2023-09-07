package com.prodapt.learning.business;
import com.prodapt.learning.*;
import com.prodapt.learning.entity.User;

import lombok.Data;
@Data
public class LoggedInUser {
	private User loggedInUser;

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
