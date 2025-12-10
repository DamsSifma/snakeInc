package org.snakeInc.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createPlayer_ShouldReturn400WithInvalidParams() {
        String invalidPayload = "{\"name\": \"\", \"age\": -5}";
        HttpEntity<String> request = createJsonRequest(invalidPayload);

        var response = restTemplate.postForEntity("/api/v1/players", request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void createPlayer_ShouldReturn200WithValidParams() {
        String validPayload = "{\"name\": \"TestPlayer\", \"age\": 20}";
        HttpEntity<String> request = createJsonRequest(validPayload);

        var response = restTemplate.postForEntity("/api/v1/players", request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    public static HttpEntity<String> createJsonRequest(String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(payload, headers);
    }
}