package com.unicordoba.agarf.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.modelos.Ejercicio;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {



    private ArrayList<Ejercicio> list;
    private Context context;
    class Imagen{
        String nombre;
        Drawable recurso;
        boolean tint;

        public Imagen(String nombre, Drawable recurso, boolean tint) {
            this.nombre = nombre;
            this.recurso = recurso;
            this.tint = tint;
        }
    }

    ArrayList<Imagen> imagens;


    public EjerciciosAdapter(ArrayList<Ejercicio> lista, Context context) {
        this.list = lista;
        this.context=context;
        imagens=new ArrayList<>();

        imagens.add(new Imagen("SENTADILLAS",context.getResources().getDrawable(R.drawable.sentadillas),true));
        imagens.add(new Imagen("SENTADILLA FRONTAL CON BARRA",context.getResources().getDrawable(R.drawable.sentadilla2),true));
        imagens.add(new Imagen("SENTADILLA SUMO",context.getResources().getDrawable(R.drawable.sentadilla3),false));
        imagens.add(new Imagen("ZANCADAS",context.getResources().getDrawable(R.drawable.zancadas),false));
        imagens.add(new Imagen("CURL FEMORAL",context.getResources().getDrawable(R.drawable.curlfemoral),false));
        imagens.add(new Imagen("EXTENCIONES DE CUADRICEBS",context.getResources().getDrawable(R.drawable.extensionpiernas),false));

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new EjercicioViewHolder(vista);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final Ejercicio ejercicio = list.get(position);



        final EjercicioViewHolder h = (EjercicioViewHolder) holder;

        h.tvNombre.setText(ejercicio.getNombre());
        h.tvSeriesRepeticiones.setText(ejercicio.getNumero_series() +" Series de "+ejercicio.getNumero_repeticiones()+" Repeticiones");
        h.tvDescripcion.setText(ejercicio.getDescripcion());
        h.tvSugerencias.setText(ejercicio.getSugerencias());

        Imagen imagen= getImagen(ejercicio.getNombre());


        if(imagen!=null){





            h.imgEjercicio.setImageDrawable(imagen.recurso);

            h.imgEjercicio.setVisibility(View.VISIBLE);


        }
        else{
            h.imgEjercicio.setVisibility(View.GONE);
        }





    }


    public Imagen getImagen(String nombre) {


        for(Imagen img: imagens)
        {
            if(img.nombre.equals(nombre))return  img;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void setItems(ArrayList<Ejercicio> ejercicios) {
        this.list = ejercicios;
        this.notifyDataSetChanged();
    }





    public class EjercicioViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre,tvSeriesRepeticiones,tvDescripcion,tvSugerencias;
        ImageView imgEjercicio;

        public EjercicioViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvSeriesRepeticiones = itemView.findViewById(R.id.tvSeriesRepeticiones);
            tvDescripcion= itemView.findViewById(R.id.tvDescripcion);
            tvSugerencias= itemView.findViewById(R.id.tvSugerencias);
            imgEjercicio= itemView.findViewById(R.id.imgEjercicio);



        }



    }
}