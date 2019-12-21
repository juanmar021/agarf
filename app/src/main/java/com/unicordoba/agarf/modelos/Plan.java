package com.unicordoba.agarf.modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Plan implements Serializable {
    private String id_plan;
    private int numero_semanas;
    private String tipo;
    private String estado;
    private int semana_actual;
    private int dias_transcurridos;
    private String fecha_inicio;

    private ArrayList<Dia>dias;

    public Plan() {
    }


    public String getId_plan() {
        return id_plan;
    }

    public void setId_plan(String id_plan) {
        this.id_plan = id_plan;
    }

    public int getNumero_semanas() {
        return numero_semanas;
    }

    public void setNumero_semanas(int numero_semanas) {
        this.numero_semanas = numero_semanas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getSemana_actual() {
        return semana_actual;
    }

    public void setSemana_actual(int semana_actual) {
        this.semana_actual = semana_actual;
    }

    public int getDias_transcurridos() {
        return dias_transcurridos;
    }

    public void setDias_transcurridos(int dias_transcurridos) {
        this.dias_transcurridos = dias_transcurridos;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }
}
