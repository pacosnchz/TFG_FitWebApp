package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByCategoria(String categoria);
}