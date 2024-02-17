package com.examen.demo.model;

import lombok.Data;

@Data
public class UserResponseDto {
	
	
	private String mensaje;
	
	private UserDto userDto;

	public UserResponseDto(String mensaje, UserDto userDto) {
		super();
		this.mensaje = mensaje;
		this.userDto = userDto;
	}

	public UserResponseDto(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	
	
	
}
