package com.fitweb.fitweb.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comidas")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String nombre;
    private int calorias;
    private double proteinas;
    private double carbos;
    private double grasas;
    private String momento; // Desayuno, Almuerzo, Cena, Snack
    private LocalDate fecha;

    public Comida() {}

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long u) { this.usuarioId = u; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public int getCalorias() { return calorias; }
    public void setCalorias(int c) { this.calorias = c; }
    public double getProteinas() { return proteinas; }
    public void setProteinas(double p) { this.proteinas = p; }
    public double getCarbos() { return carbos; }
    public void setCarbos(double c) { this.carbos = c; }
    public double getGrasas() { return grasas; }
    public void setGrasas(double g) { this.grasas = g; }
    public String getMomento() { return momento; }
    public void setMomento(String m) { this.momento = m; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate f) { this.fecha = f; }
}