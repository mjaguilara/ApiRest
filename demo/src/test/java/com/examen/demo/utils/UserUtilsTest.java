package com.examen.demo.utils;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.examen.demo.model.UserRequestDto;


public class UserUtilsTest {
	
	@Test
	public void testValidateEmail() {
	    String emailAddress = "mjaguilar@gmail.com";
	    assertTrue(UserUtils.isValidateEmail(emailAddress));
	}
	
	@Test
	public void testValidatePassword() {
	    String password = "12aaaaaaaaA";
	    assertTrue(UserUtils.isValidPassword(password));
		 
	}
	
	@Test
	public void testUerIsIncomplete() {
		UserRequestDto dto = new UserRequestDto() ;
	    dto.setEmail("mj@gmail.com");
	    dto.setNombre("maria jose");
	    dto.setPassword("123");
	    assertTrue(!UserUtils.isUerIsIncomplete(dto));
	   
	}


}
