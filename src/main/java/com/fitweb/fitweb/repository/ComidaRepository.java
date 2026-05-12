package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ComidaRepository extends JpaRepository<Comida, Long> {
    List<Comida> findByUsuarioIdAndFechaOrderByIdAsc(Long usuarioId, LocalDate fecha);
    void deleteByUsuarioIdAndFecha(Long usuarioId, LocalDate fecha);
}