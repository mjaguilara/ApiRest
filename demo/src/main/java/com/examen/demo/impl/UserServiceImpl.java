package com.examen.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examen.demo.entity.User;
import com.examen.demo.exception.Not_FoundException;
import com.examen.demo.exception.UserException;
import com.examen.demo.mapper.MapperUser;
import com.examen.demo.model.UserDto;
import com.examen.demo.model.UserRequestDto;
import com.examen.demo.model.UserResponseDto;
import com.examen.demo.repository.UserRepository;
import com.examen.demo.security.SpringSecurityConfig;
import com.examen.demo.service.IUserService;
import com.examen.demo.utils.Constantes;
import com.examen.demo.utils.UserUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
	
	 // private final AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	SpringSecurityConfig secure;

	ModelMapper mapper = new ModelMapper();
	
	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		 String token;
			log.info("Init createUser");
			if (UserUtils.isUerIsIncomplete(dto)) {
				throw new UserException(1,Constantes.REQUIERED);
			}

			if (!UserUtils.isValidateEmail(dto.getEmail())) {
				throw new UserException(2,Constantes.EMAIL_INVALID);
			}
			final List<String> listRoles = new ArrayList<>();
			    listRoles.add(Constantes.ROL_ADMIN);
			    listRoles.add(Constantes.ROL_USER);
			
			User user = userRepository.findByEmail(dto.getEmail());
			if (Objects.isNull(user)) {
				MapperUser mapping = new MapperUser();
				user = mapping.dtoConvertEntity(dto);
				token = secure.createToken(dto.getEmail(),listRoles);
				user.setToken(token);
				this.userRepository.save(user);
				
				return new UserResponseDto(Constantes.USER_OK, mapper.map(user, UserDto.class));
			} else {
				return new UserResponseDto(Constantes.EMAIL_EXIST + " "+ dto.getEmail(), null);
			}
	
	}

	

	@Override
	public List<UserDto> getAllUser() {
		try {
			log.info("init getAllUser");
			List<User> listUser = userRepository.findAll();
			List<UserDto> listUserDto = new ArrayList<>();
			if (!listUser.isEmpty()) {
				listUser.forEach(list -> {
					
					listUserDto.add(mapper.map(list, UserDto.class));
				});
			}
			log.info("finish getAllUser");
			return listUserDto;
		} catch (Exception e) {
			log.info("Error getAllUser " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public UserResponseDto updateUser(UserDto dto) {
		
			log.info("Init updateUser");
			if (UserUtils.isUerIsIncompleteUpdate(dto)) {
				throw new UserException(1,Constantes.REQUIERED);
			}

			User user = userRepository.findByEmail(dto.getEmail());
			if (!Objects.isNull(user)) {
				user.setNombre(dto.getNombre());
				user.setPassword(dto.getPassword());
				user.setLastLogin(new Date());
				user.setUpdateDate(new Date());
				user.setActive(dto.isActive());
				this.userRepository.save(user);
				return new UserResponseDto(Constantes.USER_UPDATE + " " + dto.getEmail(), mapper.map(user, UserDto.class));
			} else {
				throw new Not_FoundException(3,Constantes.USER_NOT_EXIST);
			}
	}

	@Override
	public UserResponseDto updateUserByEmail(UserRequestDto dto) {
		try {
			log.info("Init updateUserByEmail");
			if (!UserUtils.isValidateEmail(dto.getEmail())) {
				return new UserResponseDto(Constantes.EMAIL_INVALID);
			}
			User user = userRepository.findByEmail(dto.getEmail());
			if (!Objects.isNull(user)){
				return new UserResponseDto(Constantes.USER_INFO + " " + dto.getEmail(), mapper.map(user, UserDto.class));
			}else {
				return new UserResponseDto(Constantes.USER_INFO + " " + dto.getEmail());
			}
		}catch (Exception e) {
			log.info("Error updateUserByEmail " + e.getMessage());
		}
		return null;
	}
}
