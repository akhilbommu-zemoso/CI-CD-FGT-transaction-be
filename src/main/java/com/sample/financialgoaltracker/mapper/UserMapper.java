package com.sample.financialgoaltracker.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.entity.User;


@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User convertToEntity(UserDTO userDTO) {

        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user) {

        return modelMapper.map(user, UserDTO.class);
    }

}
