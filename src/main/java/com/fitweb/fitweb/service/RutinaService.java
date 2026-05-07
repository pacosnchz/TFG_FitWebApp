package com.fitweb.fitweb.service;

import com.fitweb.fitweb.model.Rutina;
import com.fitweb.fitweb.model.RutinaEjercicio;
import com.fitweb.fitweb.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    public List<Rutina> getRutinasByUsuario(Long usuarioId) {
        return rutinaRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }

    public Rutina crearRutina(Rutina rutina) {
        for (RutinaEjercicio ej : rutina.getEjercicios()) {
            ej.setRutina(rutina);
        }
        Rutina saved = rutinaRepository.save(rutina);
        rutinaRepository.flush();
        return rutinaRepository.findByIdWithEjercicios(saved.getId()).orElse(saved);
    }

    public Optional<Rutina> getRutinaById(Long id) {
        return rutinaRepository.findById(id);
    }

    public Rutina marcarCompletada(Long id, boolean completada) {
        Rutina r = rutinaRepository.findByIdWithEjercicios(id).orElseThrow();
        r.setCompletada(completada);
        Rutina saved = rutinaRepository.save(r);
        rutinaRepository.flush();
        return rutinaRepository.findByIdWithEjercicios(saved.getId()).orElse(saved);
    }

    public void eliminarRutina(Long id) {
        rutinaRepository.deleteById(id);
    }
}