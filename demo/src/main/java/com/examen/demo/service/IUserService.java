package com.examen.demo.service;

import java.util.List;

import com.examen.demo.entity.User;
import com.examen.demo.model.UserDto;
import com.examen.demo.model.UserRequestDto;
import com.examen.demo.model.UserResponseDto;



public interface IUserService {

	UserResponseDto createUser(UserRequestDto dto);

	List<UserDto> getAllUser();

	UserResponseDto updateUser(UserDto dto);

	UserResponseDto updateUserByEmail(UserRequestDto dto);

	/*User findUserByToken(String tokenSearch);

	void revocateToken(String tokenSearch, String updateToken);

	User findByEmail(String username);*/
	

}
