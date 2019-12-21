package com.unicordoba.agarf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unicordoba.agarf.R;
 import com.unicordoba.agarf.modelos.Alimento;
import com.unicordoba.agarf.servicios.ServicioWeb;

import java.util.ArrayList;
import java.util.Calendar;

public class AlimentosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {



    private ArrayList<Alimento> list;
    private Context context;



    public AlimentosAdapter(ArrayList<Alimento> lista, Context context) {
        this.list = lista;
        this.context=context;
     }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alimento, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new AlimentoViewHolder(vista);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final Alimento alimento = list.get(position);



        final AlimentoViewHolder h = (AlimentoViewHolder) holder;

        h.tvTipoHora.setText(alimento.getTipo_comida()+" de "+formatHora(alimento.getHora_minima())+" a "+formatHora(alimento.getHora_maxima()));
        h.tvContenido.setText(alimento.getContenido());
        h.tvRecomendaciones.setText(alimento.getRecomendaciones());


        if(alimento.getRecomendaciones().equals(" ") || alimento.getRecomendaciones().equals("")){
            h.tvRecomendaciones.setVisibility(View.GONE);
        }


        if(getHora()>=alimento.getHora_minima() && getHora()<=alimento.getHora_maxima()){
            h.tvTipoHora.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }




//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });


    }

    double getHora()
    {
        Calendar calendar = Calendar.getInstance();
        double hora = calendar.get(Calendar.HOUR_OF_DAY);
        double min = calendar.get(Calendar.MINUTE);

        hora=hora+(min/100);

        System.out.println("hora:::::: "+ hora);

        return  hora;
    }
    String formatHora(double hora){

        String h= String.valueOf(hora);

        h=h.replace(".0",":00");
        h=h.replace(",0",":00");

        h=h.replace(".3",":30");
        h=h.replace(".",":");
        h=h.replace(",",":");


        return  h;
    }





    @Override
    public int getItemCount() {
        return list.size();
    }



    public void setItems(ArrayList<Alimento> alimento) {
        this.list = alimento;
        this.notifyDataSetChanged();
    }





    public class  AlimentoViewHolder extends RecyclerView.ViewHolder{
        TextView tvTipoHora,tvContenido ,tvRecomendaciones;

        public AlimentoViewHolder(View itemView) {
            super(itemView);
            tvTipoHora = itemView.findViewById(R.id.tvTipoHora);
            tvContenido = itemView.findViewById(R.id.tvContenido);
            tvRecomendaciones= itemView.findViewById(R.id.tvRecomendaciones);



        }



    }
}