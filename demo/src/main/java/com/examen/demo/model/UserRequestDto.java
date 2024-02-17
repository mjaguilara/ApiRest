package com.examen.demo.model;


import java.util.List;

import com.examen.demo.entity.Phone;

import lombok.Data;

@Data
public class UserRequestDto {
	
	
	private String nombre;
	
	private String email;
	
	private String password;
	
	private List<Phone> telefonos;
	
	

}
