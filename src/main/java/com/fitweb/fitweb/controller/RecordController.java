package com.fitweb.fitweb.controller;

import com.fitweb.fitweb.model.Record;
import com.fitweb.fitweb.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "*")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Record> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return recordService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Record> crear(@RequestBody Record record) {
        return ResponseEntity.ok(recordService.guardar(record));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        recordService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}