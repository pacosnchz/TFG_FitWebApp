package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Usuario;
import com.fitweb.fitweb.repository.UsuarioRepository;
import com.fitweb.fitweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            nuevo.setPassword(null);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String password = datos.get("password");

        System.out.println("=== LOGIN ===");
        System.out.println("Email: " + email);
        System.out.println("Password recibido: " + password);

        return usuarioService.buscarPorEmail(email)
                .filter(u -> {
                    boolean ok = passwordEncoder.matches(password, u.getPassword());
                    System.out.println("Hash BD: " + u.getPassword());
                    System.out.println("¿Match?: " + ok);
                    return ok;
                })
                .map(u -> {
                    u.setPassword(null);
                    return ResponseEntity.ok(u);
                })
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(u -> { u.setPassword(null); return ResponseEntity.ok(u); })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario datos) {
        return usuarioService.buscarPorId(id).map(u -> {
            u.setNombre(datos.getNombre());
            u.setEdad(datos.getEdad());
            u.setPeso(datos.getPeso());
            u.setAltura(datos.getAltura());
            u.setObjetivo(datos.getObjetivo());
            u.setGenero(datos.getGenero());
            u.setLesiones(datos.getLesiones());
            u.setLesionNotas(datos.getLesionNotas());
            if (datos.getAvatarColor() != null) u.setAvatarColor(datos.getAvatarColor());
            if (datos.getNivelActividad() != null) u.setNivelActividad(datos.getNivelActividad());
            Usuario actualizado = usuarioService.actualizar(u);
            actualizado.setPassword(null);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> cambiarPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Usuario u = usuarioRepo.findById(id).orElseThrow();
            if (!passwordEncoder.matches(body.get("passwordActual"), u.getPassword())) {
                return ResponseEntity.badRequest().body(Map.of("message", "Contraseña actual incorrecta"));
            }
            u.setPassword(passwordEncoder.encode(body.get("passwordNueva")));
            usuarioRepo.save(u);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}