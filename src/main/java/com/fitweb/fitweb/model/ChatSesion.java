package com.fitweb.fitweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_sesiones")
public class ChatSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @Column(columnDefinition = "TEXT")
    private String mensajesJson;

    private LocalDateTime fecha;
    private int totalMensajes;

    public ChatSesion() {}

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long u) { this.usuarioId = u; }
    public String getMensajesJson() { return mensajesJson; }
    public void setMensajesJson(String m) { this.mensajesJson = m; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime f) { this.fecha = f; }
    public int getTotalMensajes() { return totalMensajes; }
    public void setTotalMensajes(int t) { this.totalMensajes = t; }
}