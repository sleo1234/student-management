package com.jrp.sma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jrp.sma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount,Long>{
	
	@Override
	public List <UserAccount> findAll();

}
