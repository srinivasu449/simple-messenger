package com.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.messenger.entity.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

	@Query("Select u FROM Users u")
	List<Users> findAllUsers();

}
