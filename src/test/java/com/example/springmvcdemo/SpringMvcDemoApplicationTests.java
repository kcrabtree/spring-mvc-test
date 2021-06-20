package com.example.springmvcdemo;

import com.example.springmvcdemo.endpoints.HealthCheckController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertNotNull;


@SpringBootTest
class SpringMvcDemoApplicationTests {

	@Autowired
	private HealthCheckController healthCheckController;

	@Test
	void contextLoads() {
		assertNotNull("HealthCheckController should be loaded into context", healthCheckController);
	}

}
