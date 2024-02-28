package com.walletService.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/user")
	public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
		return userService.createUser(userCreateRequest.to());
	}
	
	@GetMapping("/user")
	public User getUserById(@RequestParam("id") int id) {
		return userService.getUserById(id);
	}
	
}
