package com.sample.financialgoaltracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.financialgoaltracker.entity.User;

public interface UserRepository extends CrudRepository<User,Integer> {


	User getUserByAuth0Id(String authOId);

}
