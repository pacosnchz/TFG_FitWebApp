package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.ChatMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMensajeRepository extends JpaRepository<ChatMensaje, Long> {
    List<ChatMensaje> findByUsuarioIdOrderByFechaAsc(Long usuarioId);
    void deleteByUsuarioId(Long usuarioId);
}