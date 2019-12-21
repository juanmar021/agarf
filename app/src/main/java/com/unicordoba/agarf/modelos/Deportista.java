package com.unicordoba.agarf.modelos;

import android.content.ContentValues;

import java.io.Serializable;

import static com.unicordoba.agarf.basedatos.DBHelper.APELLIDOS;
import static com.unicordoba.agarf.basedatos.DBHelper.CATEGORIA;
import static com.unicordoba.agarf.basedatos.DBHelper.EDAD;
import static com.unicordoba.agarf.basedatos.DBHelper.ESTATURA;
import static com.unicordoba.agarf.basedatos.DBHelper.FECHA_NACIMIENTO;
import static com.unicordoba.agarf.basedatos.DBHelper.ID;
import static com.unicordoba.agarf.basedatos.DBHelper.NOMBRES;
import static com.unicordoba.agarf.basedatos.DBHelper.SEXO;
import static com.unicordoba.agarf.basedatos.DBHelper.TOKEN;

public class Deportista implements Serializable {

    private int id;
    private String nombres;
    private String pass;
    private String apellidos;
    private String fecha_nacimiento;
    private int estatura;
    private String sexo;
    private int edad;
    private String categoria;
    private String token;


    public Deportista() {
    }

    public Deportista(int id, String nombres, String pass, String apellidos, String fecha_nacimiento, int estatura, String sexo, int edad, String categoria, String token) {
        this.id = id;
        this.nombres = nombres;
        this.pass = pass;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estatura = estatura;
        this.sexo = sexo;
        this.edad = edad;
        this.categoria = categoria;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(ID, getId());
        values.put(NOMBRES, getNombres());
        values.put(APELLIDOS, getApellidos());
        values.put(FECHA_NACIMIENTO, getFecha_nacimiento());
        values.put(ESTATURA, getEstatura());
        values.put(SEXO, getSexo());
        values.put(EDAD, getEdad());
        values.put(CATEGORIA, getCategoria());
        values.put(TOKEN, getToken());

        return values;
    }

}
