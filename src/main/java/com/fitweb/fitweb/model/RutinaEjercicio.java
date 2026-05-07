package com.fitweb.fitweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "rutina_ejercicios")
public class RutinaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    @JsonIgnore
    private Rutina rutina;

    private String nombreEjercicio;
    private int series;
    private int repeticiones;
    private int descansoSegundos;
    private String notas;

    public RutinaEjercicio() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Rutina getRutina() { return rutina; }
    public void setRutina(Rutina rutina) { this.rutina = rutina; }
    public String getNombreEjercicio() { return nombreEjercicio; }
    public void setNombreEjercicio(String n) { this.nombreEjercicio = n; }
    public int getSeries() { return series; }
    public void setSeries(int s) { this.series = s; }
    public int getRepeticiones() { return repeticiones; }
    public void setRepeticiones(int r) { this.repeticiones = r; }
    public int getDescansoSegundos() { return descansoSegundos; }
    public void setDescansoSegundos(int d) { this.descansoSegundos = d; }
    public String getNotas() { return notas; }
    public void setNotas(String n) { this.notas = n; }
}