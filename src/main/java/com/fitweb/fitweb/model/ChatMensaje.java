package com.fitweb.fitweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_mensajes")
public class ChatMensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String rol;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDateTime fecha;

    public ChatMensaje() {}

    public ChatMensaje(Long usuarioId, String rol, String contenido) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long u) { this.usuarioId = u; }
    public String getRol() { return rol; }
    public void setRol(String r) { this.rol = r; }
    public String getContenido() { return contenido; }
    public void setContenido(String c) { this.contenido = c; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime f) { this.fecha = f; }
}