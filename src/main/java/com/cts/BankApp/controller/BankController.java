package com.cts.BankApp.controller;

import java.util.List;
import java.util.Optional;

//import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.BankApp.service.UserService;
import com.cts.BankApp.users.Users;
@CrossOrigin(origins = "http://localhost:8049")
@RestController
@Configuration
public class BankController {
	@RequestMapping("/")
	public String getHello(){
				return "Hello";
	}

	@Autowired
	UserService userService;
	
	@GetMapping("/gall")
		public ResponseEntity<List<Users>> getUsers(){
		List<Users> users=userService.getUsers();
		return ResponseEntity.ok(users);
	}
	
	
	@PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable Integer id) {
        Optional<Users> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
	
//	@PutMapping("/{id}")
//    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
////        Users updatedUser = userService.updateUser(id, userDetails);
////        return ResponseEntity.ok(updatedUser);
//		return ResponseEntity<Users>
//    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//	        userService.deleteUser(id);
	        return ResponseEntity.ok().build();
	    }
}
