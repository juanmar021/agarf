package com.unicordoba.agarf.modelos;

import java.util.ArrayList;

public class Respuesta {

    private Deportista deportista;
    private ArrayList<Plan> planes;


    public ArrayList<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<Plan> planes) {
        this.planes = planes;
    }

    public Deportista getDeportista() {
        return deportista;
    }

    public void setDeportista(Deportista deportista) {
        this.deportista = deportista;
    }
}
