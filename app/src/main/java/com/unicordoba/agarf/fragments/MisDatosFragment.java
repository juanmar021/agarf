package com.unicordoba.agarf.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.basedatos.DBManager;
import com.unicordoba.agarf.modelos.Deportista;


public class MisDatosFragment extends Fragment {


    public MisDatosFragment() {
        // Required empty public constructor
    }

    TextView tvCedula,tvNombres,tvFechaNacimiento,tvSexo,tvEdad,tvCategoria,tvEstatura;
    private DBManager dbManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_mis_datos, container, false);

        tvCedula=view.findViewById(R.id.tvId);
        tvNombres=view.findViewById(R.id.tvNombres);
        tvFechaNacimiento=view.findViewById(R.id.tvFechaNacimiento);
        tvSexo=view.findViewById(R.id.tvSexo);
        tvEdad=view.findViewById(R.id.tvEdad);
        tvCategoria=view.findViewById(R.id.tvCategoria);
        tvEstatura=view.findViewById(R.id.tvEstatura);

        dbManager = new DBManager(getContext());

        mostrarDatos();
        return  view;
    }

    private  void mostrarDatos()
    {
        Deportista usuario=dbManager.getDeportista();

        if(usuario!=null)
        {
            tvCedula.setText("ID. "+usuario.getId());
            tvNombres.setText(usuario.getNombres()+" "+usuario.getApellidos());
            tvSexo.setText("Sexo: "+usuario.getSexo());
            tvEstatura.setText("Estatura: "+usuario.getEstatura() +" cm");
            tvFechaNacimiento.setText("Fecha nacimiento: " +usuario.getFecha_nacimiento() );
            tvEdad.setText("Edad: "+usuario.getEdad() +" años");
            tvCategoria.setText("Categoria: "+usuario.getCategoria());


        }else
        {
            Toast.makeText(getContext(),"No se pudo optener el usuario",Toast.LENGTH_LONG).show();
        }

    }




}
