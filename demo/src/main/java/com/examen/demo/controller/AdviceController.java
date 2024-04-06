package com.examen.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.examen.demo.exception.Not_FoundException;
import com.examen.demo.exception.UserException;
import com.examen.demo.model.ErrorDTO;
import com.examen.demo.utils.Constantes;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
@RestController
public class AdviceController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<ErrorDTO>requestExceptionHandler(UserException ex){
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Not_FoundException.class)
	public ResponseEntity<ErrorDTO>requestExceptionHandler(Not_FoundException ex){
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDTO>requestExceptionHandlera(Exception ex){
		if (ex instanceof BadCredentialsException) {
			ErrorDTO error = ErrorDTO.builder().code(HttpStatus.UNAUTHORIZED.value()).message(Constantes.AUTHENTICATION_FAILURE).build();
			return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		}
		if (ex instanceof AccessDeniedException) {
			ErrorDTO error = ErrorDTO.builder().code(HttpStatus.FORBIDDEN.value()).message(Constantes.NO_AUTHORIZATE).build();
			return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
		if (ex instanceof SignatureException) {
			ErrorDTO error = ErrorDTO.builder().code(HttpStatus.FORBIDDEN.value()).message(Constantes.SIGNATURE_NO_VALID).build();
			return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
		if (ex instanceof ExpiredJwtException) {
			ErrorDTO error = ErrorDTO.builder().code(HttpStatus.FORBIDDEN.value()).message(Constantes.TOKEN_EXPIRED).build();
			return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
		if (ex instanceof MalformedJwtException) {
			ErrorDTO error = ErrorDTO.builder().code(HttpStatus.UNAUTHORIZED.value()).message(Constantes.TOKEN_NO_VALID).build();
			return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
}
