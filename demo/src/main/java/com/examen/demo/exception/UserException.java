package com.examen.demo.exception;

import lombok.Data;

@Data
public class UserException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private int code;

	public UserException (int code, String mensaje) {
		super(mensaje);
		this.code = code;
		
	}
		

}
