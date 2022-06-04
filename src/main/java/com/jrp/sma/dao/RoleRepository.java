package com.jrp.sma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jrp.sma.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	public Role findByName(String string);

}
