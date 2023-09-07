package com.prodapt.learning;
import java.util.Optional;
import com.prodapt.learning.*;
import com.prodapt.learning.entity.User;
import com.prodapt.learning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
        	System.out.println("no user");
            //throw new CycleShopBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

}
