package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Comida;
import com.fitweb.fitweb.repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/dieta")
public class ComidaController {

    @Autowired
    private ComidaRepository comidaRepo;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @GetMapping("/comidas/{usuarioId}")
    public ResponseEntity<?> getComidas(
            @PathVariable Long usuarioId,
            @RequestParam(defaultValue = "") String fecha) {
        LocalDate date = fecha.isEmpty() ? LocalDate.now() : LocalDate.parse(fecha);
        return ResponseEntity.ok(comidaRepo.findByUsuarioIdAndFechaOrderByIdAsc(usuarioId, date));
    }

    @PostMapping("/comidas")
    public ResponseEntity<Comida> addComida(@RequestBody Comida comida) {
        if (comida.getFecha() == null) comida.setFecha(LocalDate.now());
        return ResponseEntity.ok(comidaRepo.save(comida));
    }

    @DeleteMapping("/comidas/{id}")
    public ResponseEntity<Void> deleteComida(@PathVariable Long id) {
        comidaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generar-plan")
    public ResponseEntity<String> generarPlan(@RequestBody Map<String, Object> payload) {
        try {
            String prompt = (String) payload.get("prompt");
            String requestBody = "{\"model\":\"llama-3.3-70b-versatile\",\"max_tokens\":1500,\"messages\":[{\"role\":\"user\",\"content\":\""
                    + prompt.replace("\"", "\\\"").replace("\n", "\\n") + "\"}]}";

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