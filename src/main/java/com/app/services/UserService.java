package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.app.models.User;

public interface UserService {

	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User updateUser(UUID id,User user);
	
	void deleteUser(UUID id);
	
	Optional<User> findUser(UUID id);

    User findUserByName(String user);
}
