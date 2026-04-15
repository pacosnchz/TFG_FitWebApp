package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Ejercicio;
import com.fitweb.fitweb.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin(origins = "*")
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping
    public List<Ejercicio> obtenerTodos() {
        return ejercicioService.obtenerTodos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Ejercicio> porCategoria(@PathVariable String categoria) {
        return ejercicioService.obtenerPorCategoria(categoria);
    }

    @PostMapping
    public ResponseEntity<Ejercicio> crear(@RequestBody Ejercicio ejercicio) {
        return ResponseEntity.ok(ejercicioService.guardar(ejercicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Ejercicio datos) {
        return ejercicioService.buscarPorId(id).map(e -> {
            e.setNombre(datos.getNombre());
            e.setCategoria(datos.getCategoria());
            e.setMusculo(datos.getMusculo());
            e.setSeries(datos.getSeries());
            e.setDescripcion(datos.getDescripcion());
            e.setYoutubeid(datos.getYoutubeid());
            e.setLesionesRelacionadas(datos.getLesionesRelacionadas());
            return ResponseEntity.ok(ejercicioService.guardar(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ejercicioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}