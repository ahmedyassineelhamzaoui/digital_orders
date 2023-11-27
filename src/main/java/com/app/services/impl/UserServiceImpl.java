package com.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.User;
import com.app.repositories.UserRepository;
import com.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User updateUser(UUID id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> findUser(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
