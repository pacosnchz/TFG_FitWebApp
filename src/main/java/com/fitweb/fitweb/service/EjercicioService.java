package com.fitweb.fitweb.service;

import com.fitweb.fitweb.model.Ejercicio;
import com.fitweb.fitweb.repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    public List<Ejercicio> obtenerTodos() {
        return ejercicioRepository.findAll();
    }

    public List<Ejercicio> obtenerPorCategoria(String categoria) {
        return ejercicioRepository.findByCategoria(categoria);
    }

    public Ejercicio guardar(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    public Optional<Ejercicio> buscarPorId(Long id) {
        return ejercicioRepository.findById(id);
    }

    public void eliminar(Long id) {
        ejercicioRepository.deleteById(id);
    }
}