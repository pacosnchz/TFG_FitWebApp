package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.ChatMensaje;
import com.fitweb.fitweb.model.ChatSesion;
import com.fitweb.fitweb.repository.ChatMensajeRepository;
import com.fitweb.fitweb.repository.ChatSesionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Autowired
    private ChatMensajeRepository chatRepo;

    @Autowired
    private ChatSesionRepository sesionRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/historial/{usuarioId}")
    public ResponseEntity<?> getHistorial(@PathVariable Long usuarioId) {
        List<ChatMensaje> mensajes = chatRepo.findByUsuarioIdOrderByFechaAsc(usuarioId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (ChatMensaje m : mensajes) {
            Map<String, Object> msg = new LinkedHashMap<>();
            msg.put("rol", m.getRol());
            msg.put("contenido", m.getContenido());
            msg.put("fecha", m.getFecha().toString());
            result.add(msg);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/historial/{usuarioId}")
    public ResponseEntity<Void> limpiarHistorial(@PathVariable Long usuarioId) {
        try {
            List<ChatMensaje> mensajes = chatRepo.findByUsuarioIdOrderByFechaAsc(usuarioId);
            if (!mensajes.isEmpty()) {
                // Guardar sesion antes de borrar
                ChatSesion sesion = new ChatSesion();
                sesion.setUsuarioId(usuarioId);
                sesion.setFecha(LocalDateTime.now());
                sesion.setTotalMensajes(mensajes.size());

                List<Map<String, Object>> msgs = new ArrayList<>();
                for (ChatMensaje m : mensajes) {
                    Map<String, Object> msg = new LinkedHashMap<>();
                    msg.put("rol", m.getRol());
                    msg.put("contenido", m.getContenido());
                    msg.put("fecha", m.getFecha().toString());
                    msgs.add(msg);
                }
                sesion.setMensajesJson(mapper.writeValueAsString(msgs));
                sesionRepo.save(sesion);
            }
            chatRepo.deleteByUsuarioId(usuarioId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sesiones/{usuarioId}")
    public ResponseEntity<?> getSesiones(@PathVariable Long usuarioId) {
        LocalDateTime hace14dias = LocalDateTime.now().minusDays(14);
        List<ChatSesion> sesiones = sesionRepo
                .findByUsuarioIdAndFechaAfterOrderByFechaDesc(usuarioId, hace14dias);
        List<Map<String, Object>> result = new ArrayList<>();
        for (ChatSesion s : sesiones) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", s.getId());
            item.put("fecha", s.getFecha().toString());
            item.put("totalMensajes", s.getTotalMensajes());
            result.add(item);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/sesiones/{sesionId}/restaurar")
    public ResponseEntity<Void> restaurarSesion(
            @PathVariable Long sesionId,
            @RequestBody Map<String, Object> body) {
        try {
            Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
            ChatSesion sesion = sesionRepo.findById(sesionId).orElseThrow();

            // Borrar historial actual
            chatRepo.deleteByUsuarioId(usuarioId);

            // Restaurar mensajes de la sesion
            JsonNode msgs = mapper.readTree(sesion.getMensajesJson());
            for (JsonNode m : msgs) {
                ChatMensaje msg = new ChatMensaje(
                        usuarioId,
                        m.get("rol").asText(),
                        m.get("contenido").asText()
                );
                msg.setFecha(LocalDateTime.parse(m.get("fecha").asText().substring(0, 19)));
                chatRepo.save(msg);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/mensaje")
    public ResponseEntity<String> mensaje(@RequestBody Map<String, Object> payload) {
        try {
            Long usuarioId = payload.get("usuarioId") != null
                    ? Long.valueOf(payload.get("usuarioId").toString()) : null;
            String userMsg = (String) payload.get("userMessage");
            Object messages = payload.get("messages");

            String messagesJson = mapper.writeValueAsString(messages);
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

            if (usuarioId != null && userMsg != null) {
                chatRepo.save(new ChatMensaje(usuarioId, "user", userMsg));
                JsonNode root = mapper.readTree(response.body());
                String assistantReply = root.path("choices").get(0).path("message").path("content").asText();
                if (!assistantReply.isEmpty()) {
                    chatRepo.save(new ChatMensaje(usuarioId, "assistant", assistantReply));
                }
            }

            return ResponseEntity.ok(response.body());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}