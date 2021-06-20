package com.example.springmvcdemo.service;

import com.example.springmvcdemo.contracts.HealthCheckDTO;
import com.example.springmvcdemo.contracts.HealthCheckType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertNull;

/**
 * Tests {@link HealthService}
 */
public class HealthServiceTest {

    HealthService healthService = new HealthService();

    @Test
    public void testCheck_whenTypeShort_thenReturnsOnlyStatus() {
        HealthCheckDTO dto = healthService.check(HealthCheckType.SHORT);

        assertEquals("OK", dto.getStatus(), "status should be 'OK'");
        assertNull("currentTime should not be set for short responses", dto.getCurrentTime());
    }

    @Test
    public void testCheck_whenTypeFull_thenReturnsFullObject() {
        HealthCheckDTO dto = healthService.check(HealthCheckType.FULL);
        assertEquals("OK", dto.getStatus(), "status should be 'OK'");
        // there's most likely a better way to assert this time property. in the interest of time, lets just check it
        // is set. control test is also verifying value
        assertNotNull("currentTime should be set", dto.getCurrentTime());
    }
}
