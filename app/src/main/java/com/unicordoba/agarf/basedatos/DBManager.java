package com.unicordoba.agarf.basedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.unicordoba.agarf.modelos.Deportista;

import java.util.ArrayList;

import static com.unicordoba.agarf.basedatos.DBHelper.APELLIDOS;
import static com.unicordoba.agarf.basedatos.DBHelper.CATEGORIA;
import static com.unicordoba.agarf.basedatos.DBHelper.EDAD;
import static com.unicordoba.agarf.basedatos.DBHelper.ESTATURA;
import static com.unicordoba.agarf.basedatos.DBHelper.FECHA_NACIMIENTO;
import static com.unicordoba.agarf.basedatos.DBHelper.ID;
import static com.unicordoba.agarf.basedatos.DBHelper.NOMBRES;
import static com.unicordoba.agarf.basedatos.DBHelper.SEXO;
import static com.unicordoba.agarf.basedatos.DBHelper.TABLA_DEPORSTISTAS;
import static com.unicordoba.agarf.basedatos.DBHelper.TOKEN;


public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
    }

    public Deportista getDeportista() {
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM " + TABLA_DEPORSTISTAS,
                null
        );

        Deportista deportista = null;

        if (c.moveToFirst()) {
            deportista = new Deportista();

            deportista.setId(c.getInt(c.getColumnIndex(ID)));
            deportista.setNombres(c.getString(c.getColumnIndex(NOMBRES)));
            deportista.setApellidos(c.getString(c.getColumnIndex(APELLIDOS)));
            deportista.setFecha_nacimiento(c.getString(c.getColumnIndex(FECHA_NACIMIENTO)));
            deportista.setEstatura(c.getInt(c.getColumnIndex(ESTATURA)));
            deportista.setSexo(c.getString(c.getColumnIndex(SEXO)));
            deportista.setEdad(c.getInt(c.getColumnIndex(EDAD)));
            deportista.setCategoria(c.getString(c.getColumnIndex(CATEGORIA)));
            deportista.setToken(c.getString(c.getColumnIndex(TOKEN)));

        }

        c.close();

        return deportista;
    }



    public long insertarDeportista(Deportista deportista) {
        db = helper.getWritableDatabase();

        long resInsert = db.insert(TABLA_DEPORSTISTAS, null, deportista.toContentValues());


        return resInsert;
    }


    public long borrarDatosTabla(String tabla) {
        db = helper.getWritableDatabase();


        return db.delete(tabla, null, null);
    }
    public void borrarTabla(String tabla)
    {
        db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM "+tabla);
    }




}
