package com.walletService.user;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final String USER_CREATE = "user_create";
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public User createUser(User user) {
		
		user = userRepo.save(user);
		
		
		//Publishing to kafka
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", user.getId());
		jsonObject.put("userEmail", user.getEmail());
		jsonObject.put("userContact", user.getContact());
		kafkaTemplate.send(USER_CREATE, jsonObject.toString());
		
		
		
		return user;
	}

	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}

}
