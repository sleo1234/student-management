package com.jrp.sma.dao;

import org.springframework.data.repository.CrudRepository;

import com.jrp.sma.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
	User save(User user);

}
