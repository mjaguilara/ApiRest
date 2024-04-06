package com.examen.demo.utils;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examen.demo.model.UserDto;
import com.examen.demo.model.UserRequestDto;
import com.examen.demo.model.UserResponseDto;



public class UserUtils {
	
	private UserUtils() {
		
	}
	
	public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
		return new ResponseEntity<String>("Mensaje: "+ message,httpStatus);
	}
	
	public static ResponseEntity<UserResponseDto> getResponseEntityDto(UserResponseDto userDtoResponse, HttpStatus httpStatus){
		return new ResponseEntity<UserResponseDto>(httpStatus);
	}
	
	
	public static boolean isValidateEmail(String emailAddress) {
	    return Pattern.compile("^(.+)@(\\S+)$")
	      .matcher(emailAddress)
	      .matches();
	}
	
	public static boolean isValidPassword(String password) {
	    return Pattern.compile("^(?=.[0-9])(?=.[a-z])(?=.*[A-Z])"
        		+ "(?=\\S+$).{8,}$")
	      .matcher(password)
	      .matches();
	}
	
	public static boolean isUerIsIncomplete(UserRequestDto dto) {
		return dto == null || dto.getEmail() == null || dto.getEmail().isBlank() || dto.getEmail().equals("") 
				|| dto.getNombre() == null || dto.getNombre().isBlank() || dto.getNombre().equals("")
				|| dto.getPassword() == null || dto.getPassword().isBlank();
	}
	
	public static boolean isUerIsIncompleteUpdate(UserDto dto) {
		return dto == null || dto.getEmail() == null || dto.getEmail().isBlank()
				|| dto.getNombre() == null || dto.getNombre().isBlank()
				|| dto.getPassword() == null || dto.getPassword().isBlank();
	}
	

}
