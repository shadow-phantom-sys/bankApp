package com.cts.controller;

import java.util.List;

//import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.userService.*;
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
	
	@Autowired
	UserService userService;
	@PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        Users updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return ResponseEntity.ok().build();
	    }
}
