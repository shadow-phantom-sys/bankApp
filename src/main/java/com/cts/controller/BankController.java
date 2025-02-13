package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.repository.UserRepository;
import com.cts.users.Users;
@CrossOrigin(origins = "http://localhost:8049")
@RestController
@Configuration
public class BankController {
	@RequestMapping("/")
	public String getHello(){
				return "Hello";
	}
	@Autowired
	UserRepository userRepository;
	@GetMapping("/gall")
		public List<Users> getUsers(){
		return userRepository.findAll();
	}
}
