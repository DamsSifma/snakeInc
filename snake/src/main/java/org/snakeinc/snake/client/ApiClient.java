package org.snakeinc.snake.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ApiClient {
    private final HttpClient http;
    private final ObjectMapper mapper;
    private final String baseUrl; // ex: "http://localhost:8080/api/v1/scores"

    public ApiClient(String baseUrl) {
        this.http = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public boolean sendScore(ScoreDto dto) {
        try {
            String json = mapper.writeValueAsString(dto);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            return resp.statusCode() >= 200 && resp.statusCode() < 300;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Integer> fetchBestScore(int playerId, String snakeName) {
        try {
            String uri = baseUrl + "?playerId=" + playerId + (snakeName != null ? "&snake=" + java.net.URLEncoder.encode(snakeName, StandardCharsets.UTF_8) : "");
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) return Optional.empty();
            var node = mapper.readTree(resp.body());
            if (!node.isArray()) return Optional.empty();
            int max = Integer.MIN_VALUE;
            boolean found = false;
            for (var el : node) {
                if (el.has("score")) {
                    int s = el.get("score").asInt();
                    if (s > max) max = s;
                    found = true;
                }
            }
            return found ? Optional.of(max) : Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<PlayerDto> fetchAllPlayers() {
        try {
            // baseUrl is for scores, derive players URL
            String playersUrl = baseUrl.replace("/scores", "/players");
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(playersUrl))
                    .GET()
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) return Collections.emptyList();
            return mapper.readValue(resp.body(), new TypeReference<List<PlayerDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
