package com.examen.demo.exception;

import lombok.Data;

@Data
public class Not_FoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private int code;

	public Not_FoundException (int code, String mensaje) {
		super(mensaje);
		this.code = code;
		
	}
		

}
