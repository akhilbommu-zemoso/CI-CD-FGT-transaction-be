package com.sample.financialgoaltracker.service;

import java.util.List;

import com.sample.financialgoaltracker.dto.UserDTO;

public interface UserService {
        
	List<UserDTO> findAll();
	
	UserDTO findById(int id);
	
	UserDTO save(UserDTO userDTO);
	
	UserDTO getUserByAuth0Id(String auth0Id);
	
	void delete(UserDTO userDTO);
}
