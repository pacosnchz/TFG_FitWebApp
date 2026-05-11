package com.fitweb.fitweb.repository;

import com.fitweb.fitweb.model.ChatSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ChatSesionRepository extends JpaRepository<ChatSesion, Long> {
    List<ChatSesion> findByUsuarioIdAndFechaAfterOrderByFechaDesc(Long usuarioId, LocalDateTime desde);
}
