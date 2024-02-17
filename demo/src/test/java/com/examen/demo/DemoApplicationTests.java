package com.examen.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {
	
	@InjectMocks
	DemoApplication manager;

	

	/*@Test
	void contextLoads() {
	}*/


	public void setUp() {
		//MockitoAnnotations.initMocks(this);
	}

	@Test
	public void checkInstance() {
		assertNotNull(manager);
	}

	@Test
	public void checkNewInstance() {
		assertNotNull(new DemoApplication());
	}
}


