package com.examen.demo.mapper;


import java.util.Date;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import com.examen.demo.entity.User;
import com.examen.demo.model.UserRequestDto;


public class MapperUser {
	
	ModelMapper mapper = new ModelMapper();
	
	
	 public User dtoConvertEntity (UserRequestDto dto) {
		User user = mapper.map(dto,User.class);
		Date now = new Date();
		user.setId(UUID.randomUUID());
		user.setCreationDate(now);
		user.setLastLogin(now);
		user.setActive(true);
		user.setTelefonos(dto.getTelefonos());
	return user;
	}

}
