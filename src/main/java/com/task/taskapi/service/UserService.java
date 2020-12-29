package com.task.taskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taskapi.entity.User;
import com.task.taskapi.repository.UserRepository;
import com.task.taskapi.service.exception.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	
	public User findOne(Integer id) {
		Optional<User> user = this.userRepository.findById(id);
		
		return user.orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
	}
	
	public User save(User dto) {
		dto.setId(null);
		User userToSave = this.fromDto(dto);

		return this.userRepository.save(userToSave);
	}
	
	public User update(Integer id, User dto) {
		this.findOne(id);

		dto.setId(id);
		User user = this.fromDtoUpdate(dto);

		return this.userRepository.save(user);
	}
	
	public void delete(Integer id) {
		Optional<User> userExists = this.userRepository.findById(id);
		
		if(userExists.isEmpty()) {
			new NotFoundException("User with id " + id + " not found");
		} else {
			this.userRepository.delete(userExists.get());
		}
	}
	
	public User fromDto(User user) {
		return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword());
	}
	
	public User fromDtoUpdate(User user) {
		return new User(user.getId(), user.getName(), user.getEmail());
	}
}
