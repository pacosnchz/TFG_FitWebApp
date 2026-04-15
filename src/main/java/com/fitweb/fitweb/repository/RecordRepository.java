package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUsuarioId(Long usuarioId);
}