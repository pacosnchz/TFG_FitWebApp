package com.fitweb.fitweb.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String categoria; // fuerza, cardio, estiramiento, movilidad
    private String musculo;
    private String series;
    private String icono;
    private String descripcion;
    private String youtubeid;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ejercicio_lesiones", joinColumns = @JoinColumn(name = "ejercicio_id"))
    @Column(name = "lesion")
    private List<String> lesionesRelacionadas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMusculo() { return musculo; }
    public void setMusculo(String musculo) { this.musculo = musculo; }
    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }
    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getYoutubeid() { return youtubeid; }
    public void setYoutubeid(String youtubeid) { this.youtubeid = youtubeid; }
    public List<String> getLesionesRelacionadas() { return lesionesRelacionadas; }
    public void setLesionesRelacionadas(List<String> lesionesRelacionadas) { this.lesionesRelacionadas = lesionesRelacionadas; }
}