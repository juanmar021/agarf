package com.unicordoba.agarf.servicios;





import com.unicordoba.agarf.modelos.Respuesta;

import java.util.ArrayList;

public class ResServer {
    private boolean okay;
    private int id;
//     private Usuario usuario;
    private Respuesta respuesta;
     private String mensaje;



// private ArrayList<Clientes> clientes;


   // public ArrayList<Clientes> getClientes() {
//        return clientes;
//    }

//    public void setClientes(ArrayList<Clientes> clientes) {
//        this.clientes = clientes;
//    }


    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isOkay() {
        return okay;
    }

    public void setOkay(boolean okay) {
        this.okay = okay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
