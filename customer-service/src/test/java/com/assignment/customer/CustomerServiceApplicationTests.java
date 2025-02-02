package com.assignment.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class CustomerServiceApplicationTests {
	@Test
	void contextLoads() {
		// Test if Spring Boot context loads successfully
	}
}