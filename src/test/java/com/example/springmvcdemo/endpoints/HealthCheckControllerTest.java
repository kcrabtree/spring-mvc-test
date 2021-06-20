package com.example.springmvcdemo.endpoints;

import com.example.springmvcdemo.contracts.HealthCheckDTO;
import com.example.springmvcdemo.service.HealthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests {@link HealthCheckController}
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthCheckController.class)
public class HealthCheckControllerTest {

    private final static String BASE_URL = "/healthcheck";
    private final static String SHORT_URL = BASE_URL + "?type=short";
    private final static String FULL_URL = BASE_URL + "?type=full";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HealthService healthService;

    private HealthCheckDTO shortResponse = HealthCheckDTO.builder().status("OK").build();
    private HealthCheckDTO fullResponse = HealthCheckDTO.builder().status("OK").currentTime(LocalDateTime.now()).build();

    @Test
    public void testHealthcheck_whenPUT_thenReturns405() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(SHORT_URL)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testHealthcheck_whenPOST_thenReturns405() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SHORT_URL)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testHealthcheck_whenPATCH_thenReturns405() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(SHORT_URL)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testHealthcheck_whenDELETE_thenReturns405() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(SHORT_URL)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testHealthcheck_whenInvalidTypeProvided_thenReturns400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?type=other")).andExpect(status().isBadRequest());
    }

    @Test
    public void testHealthcheck_whenShortTypeProvided_thenReturns200WithStatus() throws Exception {
        when(healthService.check(any())).thenReturn(shortResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(SHORT_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("status").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("currentTime").doesNotHaveJsonPath());
    }

    @Test
    public void testHealthcheck_whenFullTypeProvided_thenReturns200WithStatusAndCurrentTime() throws Exception {
        when(healthService.check(any())).thenReturn(fullResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(FULL_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("status").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("currentTime").value(fullResponse.getCurrentTime().toString()));
    }
}
