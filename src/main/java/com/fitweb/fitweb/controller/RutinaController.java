package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Rutina;
import com.fitweb.fitweb.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getRutinas(@PathVariable Long usuarioId) {
        try {
            java.util.List<Map<String,Object>> result = new java.util.ArrayList<>();
            for (Rutina saved : rutinaService.getRutinasByUsuario(usuarioId)) {
                Map<String, Object> r = new java.util.LinkedHashMap<>();
                r.put("id", saved.getId());
                r.put("nombre", saved.getNombre());
                r.put("diaSemana", saved.getDiaSemana());
                r.put("fecha", saved.getFecha() != null ? saved.getFecha().toString() : null);
                r.put("completada", saved.isCompletada());
                r.put("descripcion", saved.getDescripcion());
                r.put("usuarioId", saved.getUsuarioId());
                java.util.List<Map<String,Object>> ejercicios = new java.util.ArrayList<>();
                for (var ej : saved.getEjercicios()) {
                    Map<String,Object> e = new java.util.LinkedHashMap<>();
                    e.put("id", ej.getId());
                    e.put("nombreEjercicio", ej.getNombreEjercicio());
                    e.put("series", ej.getSeries());
                    e.put("repeticiones", ej.getRepeticiones());
                    e.put("descansoSegundos", ej.getDescansoSegundos());
                    e.put("notas", ej.getNotas());
                    ejercicios.add(e);
                }
                r.put("ejercicios", ejercicios);
                result.add(r);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearRutina(@RequestBody Rutina rutina) {
        try {
            Rutina saved = rutinaService.crearRutina(rutina);
            Map<String, Object> response = new java.util.LinkedHashMap<>();
            response.put("id", saved.getId());
            response.put("nombre", saved.getNombre());
            response.put("diaSemana", saved.getDiaSemana());
            response.put("fecha", saved.getFecha() != null ? saved.getFecha().toString() : null);
            response.put("completada", saved.isCompletada());
            response.put("descripcion", saved.getDescripcion());
            response.put("usuarioId", saved.getUsuarioId());
            java.util.List<Map<String,Object>> ejercicios = new java.util.ArrayList<>();
            for (var ej : saved.getEjercicios()) {
                Map<String,Object> e = new java.util.LinkedHashMap<>();
                e.put("id", ej.getId());
                e.put("nombreEjercicio", ej.getNombreEjercicio());
                e.put("series", ej.getSeries());
                e.put("repeticiones", ej.getRepeticiones());
                e.put("descansoSegundos", ej.getDescansoSegundos());
                e.put("notas", ej.getNotas());
                ejercicios.add(e);
            }
            response.put("ejercicios", ejercicios);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<?> completar(@PathVariable Long id, @RequestBody Map<String,Boolean> body) {
        try {
            Rutina saved = rutinaService.marcarCompletada(id, body.get("completada"));
            Map<String, Object> response = new java.util.LinkedHashMap<>();
            response.put("id", saved.getId());
            response.put("nombre", saved.getNombre());
            response.put("diaSemana", saved.getDiaSemana());
            response.put("fecha", saved.getFecha() != null ? saved.getFecha().toString() : null);
            response.put("completada", saved.isCompletada());
            response.put("descripcion", saved.getDescripcion());
            response.put("usuarioId", saved.getUsuarioId());
            java.util.List<Map<String,Object>> ejercicios = new java.util.ArrayList<>();
            for (var ej : saved.getEjercicios()) {
                Map<String,Object> e = new java.util.LinkedHashMap<>();
                e.put("id", ej.getId());
                e.put("nombreEjercicio", ej.getNombreEjercicio());
                e.put("series", ej.getSeries());
                e.put("repeticiones", ej.getRepeticiones());
                e.put("descansoSegundos", ej.getDescansoSegundos());
                e.put("notas", ej.getNotas());
                ejercicios.add(e);
            }
            response.put("ejercicios", ejercicios);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generar-ia")
    public ResponseEntity<String> generarConIA(@RequestBody Map<String, Object> payload) {
        try {
            String prompt = (String) payload.get("prompt");

            String requestBody = "{"
                    + "\"model\":\"llama-3.3-70b-versatile\","
                    + "\"max_tokens\":1000,"
                    + "\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt.replace("\"", "\\\"").replace("\n", "\\n") + "\"}]"
                    + "}";

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