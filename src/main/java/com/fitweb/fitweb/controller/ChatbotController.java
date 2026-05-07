package com.fitweb.fitweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Value("${groq.api.key}")
    private String groqApiKey;

    @PostMapping("/mensaje")
    public ResponseEntity<String> mensaje(@RequestBody Map<String, Object> payload) {
        try {
            Object messages = payload.get("messages");
            String messagesJson = new com.fasterxml.jackson.databind.ObjectMapper()
                    .writeValueAsString(messages);

            String requestBody = "{\"model\":\"llama-3.3-70b-versatile\",\"max_tokens\":700,\"messages\":" + messagesJson + "}";

            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("https://api.groq.com/openai/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + groqApiKey)
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            java.net.http.HttpResponse<String> response = client.send(request,
                    java.net.http.HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.ok(response.body());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}