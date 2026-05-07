package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    @Query("SELECT r FROM Rutina r LEFT JOIN FETCH r.ejercicios WHERE r.usuarioId = :usuarioId ORDER BY r.fecha DESC")
    List<Rutina> findByUsuarioIdOrderByFechaDesc(@Param("usuarioId") Long usuarioId);

    @Query("SELECT r FROM Rutina r LEFT JOIN FETCH r.ejercicios WHERE r.id = :id")
    Optional<Rutina> findByIdWithEjercicios(@Param("id") Long id);
}