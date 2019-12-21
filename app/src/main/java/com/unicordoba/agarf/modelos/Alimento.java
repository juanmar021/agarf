package com.unicordoba.agarf.modelos;

import java.io.Serializable;

public class Alimento implements Serializable {
    private String id;
    private String id_dia;
    private double hora_minima;
    private double hora_maxima;
    private String contenido;
    private String tipo_comida;
    private String prioridad;
    private String recomendaciones;

    public Alimento() {
    }

    public Alimento(String id, String id_dia, double hora_minima, double hora_maxima, String contenido, String tipo_comida, String prioridad, String recomendaciones) {
        this.id = id;
        this.id_dia = id_dia;
        this.hora_minima = hora_minima;
        this.hora_maxima = hora_maxima;
        this.contenido = contenido;
        this.tipo_comida = tipo_comida;
        this.prioridad = prioridad;
        this.recomendaciones = recomendaciones;
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

    public double getHora_minima() {
        return hora_minima;
    }

    public void setHora_minima(double hora_minima) {
        this.hora_minima = hora_minima;
    }

    public double getHora_maxima() {
        return hora_maxima;
    }

    public void setHora_maxima(double hora_maxima) {
        this.hora_maxima = hora_maxima;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTipo_comida() {
        return tipo_comida;
    }

    public void setTipo_comida(String tipo_comida) {
        this.tipo_comida = tipo_comida;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
}
