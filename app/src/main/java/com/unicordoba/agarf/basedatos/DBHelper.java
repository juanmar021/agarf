package com.unicordoba.agarf.basedatos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String NOMBRE_DB = "agarf.db";
    private final static int VERSION_DB = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    public static final String TABLA_DEPORSTISTAS = "deportistas";

     public static final String ID = "id";
     public static final String NOMBRES = "nombres";
     public static final String APELLIDOS = "apellidos";
     public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
     public static final String ESTATURA = "estatura";
     public static final String SEXO = "sexo";
     public static final String EDAD = "edad";
     public static final String CATEGORIA = "categoria";
     public static final String TOKEN = "token";





    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAR_TABLA_DEPORTISTA = "CREATE TABLE  IF NOT EXISTS "+ TABLA_DEPORSTISTAS +" ( " +
                 ID + " INTEGER, "+
                 NOMBRES + " TEXT, " +
                 APELLIDOS + " TEXT, " +
                 FECHA_NACIMIENTO + " TEXT, " +
                 ESTATURA + " INTEGER, " +
                 SEXO + " TEXT, " +
                 EDAD + " INTEGER, " +
                 CATEGORIA + " TEXT, " +
                 TOKEN + " TEXT " +
                 "); ";



        db.execSQL(CREAR_TABLA_DEPORTISTA);


    }
    //Inside your SQLite helper class
    @Override
    public synchronized void close () {
        if (this != null) {
            this.close();
            super.close();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }
}
