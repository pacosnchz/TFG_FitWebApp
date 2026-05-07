package com.fitweb.fitweb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String nombre;
    private String diaSemana;
    private LocalDate fecha;
    private boolean completada;
    private String descripcion;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutinaEjercicio> ejercicios = new ArrayList<>();

    public Rutina() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long u) { this.usuarioId = u; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String d) { this.diaSemana = d; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate f) { this.fecha = f; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean c) { this.completada = c; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public List<RutinaEjercicio> getEjercicios() { return ejercicios; }
    public void setEjercicios(List<RutinaEjercicio> e) { this.ejercicios = e; }
}