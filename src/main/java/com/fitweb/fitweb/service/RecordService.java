package com.fitweb.fitweb.service;

import com.fitweb.fitweb.model.Record;
import com.fitweb.fitweb.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public List<Record> obtenerPorUsuario(Long usuarioId) {
        return recordRepository.findByUsuarioId(usuarioId);
    }

    public Record guardar(Record record) {
        return recordRepository.save(record);
    }

    public void eliminar(Long id) {
        recordRepository.deleteById(id);
    }
}