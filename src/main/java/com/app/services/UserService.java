package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.User;
import com.app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	public User addUser(User user) {
		return userRepository.save(user);
	}
	 public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return updatedUser;
    }
}
