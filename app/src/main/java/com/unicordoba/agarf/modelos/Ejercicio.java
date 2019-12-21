package com.unicordoba.agarf.modelos;

public class Ejercicio {
    private String id;
    private String id_dia;
    private String nombre;
    private String descripcion;
    private int numero_series;
    private int numero_repeticiones;
    private String sugerencias;

    public Ejercicio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dia() {
        return id_dia;
    }

    public void setId_dia(String id_dia) {
        this.id_dia = id_dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero_series() {
        return numero_series;
    }

    public void setNumero_series(int numero_series) {
        this.numero_series = numero_series;
    }

    public int getNumero_repeticiones() {
        return numero_repeticiones;
    }

    public void setNumero_repeticiones(int numero_repeticiones) {
        this.numero_repeticiones = numero_repeticiones;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }
}
