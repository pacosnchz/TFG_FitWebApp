package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Usuario;
import com.fitweb.fitweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
    public ResponseEntity<?> login(@RequestBody Usuario datos) {
        return usuarioService.buscarPorEmail(datos.getEmail())
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
            u.setLesiones(datos.getLesiones());
            u.setLesionNotas(datos.getLesionNotas());
            Usuario actualizado = usuarioService.actualizar(u);
            actualizado.setPassword(null);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}