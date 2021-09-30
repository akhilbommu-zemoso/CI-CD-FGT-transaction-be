package com.sample.financialgoaltracker.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> findAll() {
		
		List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = (List<User>) userRepository.findAll();
    
        for (User user : users) {

            UserDTO userDTO = userMapper.convertToDTO(user);

            userDTOs.add(userDTO);
        }
        return userDTOs;
        
    }

	@Override
	public UserDTO findById(int id) {
		
        return userMapper.convertToDTO(userRepository.findById(id).get());
        
	}

	@Override
	public UserDTO save(UserDTO userDTO) {

		 User user = userMapper.convertToEntity(userDTO);

		LOGGER.info("user{}",user);
		 UserDTO check=getUserByAuth0Id(userDTO.getAuthOId());
		 if(check==null) {
	          userRepository.save(user);
	          return userMapper.convertToDTO(user);
		 }
		 else {
			 return getUserByAuth0Id(userDTO.getAuthOId());
		 }
	}

	@Override
	public void delete(UserDTO userDTO) {
		
        userRepository.delete(userMapper.convertToEntity(userDTO));

	}



	@Override
	public UserDTO getUserByAuth0Id(String auth0Id){
		User user = userRepository.getUserByAuth0Id(auth0Id);
		if(user==null){
			return null;
		}
		else {
			return userMapper.convertToDTO(user);
		}
	}



}
