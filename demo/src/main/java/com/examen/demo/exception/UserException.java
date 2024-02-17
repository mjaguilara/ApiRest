package com.examen.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UserException (String mensaje) {
		super(mensaje);
		
	}
		

}
