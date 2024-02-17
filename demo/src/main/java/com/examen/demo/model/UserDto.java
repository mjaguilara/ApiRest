package com.examen.demo.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.examen.demo.entity.Phone;

import lombok.Data;

@Data
public class UserDto {

	
	private UUID id;
	private String nombre;
	private String email;
	private String password;
	private Date creationDate;
	private Date updateDate;
	private Date lastLogin;
	private String token;
	private boolean active;
	private List<Phone> telefonos;
	
}
