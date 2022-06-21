package com.jrp.sma.api.comtrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.sma.entities.User;
import com.jrp.sma.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
	@Autowired UserService userService;
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		return userService.save(user);
	}

}
