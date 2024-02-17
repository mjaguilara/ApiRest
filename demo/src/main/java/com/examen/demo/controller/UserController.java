package com.examen.demo.controller;


import org.springframework.http.ResponseEntity;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.examen.demo.model.UserDto;
import com.examen.demo.model.UserRequestDto;
import com.examen.demo.model.UserResponseDto;
import com.examen.demo.security.SpringSecurityConfig;
import com.examen.demo.service.IUserService;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	SpringSecurityConfig secure;

	
	@PostMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto) {
		UserResponseDto response = userService.createUser(dto);
		return new ResponseEntity<UserResponseDto>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<UserDto>> getAll(@RequestHeader("Authorization") String token){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	
	@PutMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserResponseDto> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserDto dto){
		return ResponseEntity.ok(this.userService.updateUser(dto));
	}
	
	
	@GetMapping(value = "/v1/byEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserResponseDto> getUserByEmail(@RequestHeader("Authorization") String token, @RequestBody UserRequestDto dto){
		return ResponseEntity.ok(this.userService.updateUserByEmail(dto));
	}
	
	
	
}
