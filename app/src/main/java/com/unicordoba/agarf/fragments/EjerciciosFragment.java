package com.unicordoba.agarf.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.activitys.MainActivity;
import com.unicordoba.agarf.adapters.AlimentosAdapter;
import com.unicordoba.agarf.adapters.EjerciciosAdapter;
import com.unicordoba.agarf.modelos.Alimento;
import com.unicordoba.agarf.modelos.Dia;
import com.unicordoba.agarf.modelos.Ejercicio;
import com.unicordoba.agarf.modelos.Plan;
import com.unicordoba.agarf.servicios.ResServer;
import com.unicordoba.agarf.servicios.ServicioWeb;
import com.unicordoba.agarf.servicios.ServicioWebUtils;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EjerciciosFragment extends Fragment   implements TabHost.OnTabChangeListener, View.OnClickListener {

    public EjerciciosFragment() {
        // Required empty public constructor
    }


    TextView textView;
    TextView tvRecomendaciones;

    private FragmentTabHost tabHost;
    final String []dias={"Lunes", "Martes", "Miercoles","Jueves", "Viernes","Sabado","Domingo"};


    ArrayList<Ejercicio> ejercicios;
    public EjerciciosAdapter mAdapter;
    private ServicioWeb servicioWeb;
    private LinearLayout lnCargando;
    private LinearLayout lnSinEntrenamiento;

    public  static Plan plan= null;
    android.widget.HorizontalScrollView scrollView;
    LinearLayout lnGenerarPlan;


    Button btnGenerarPlan;
    ProgressBar pgbGenerandoPlan;



    private RecyclerView rcEjercicios;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ejercicios, container, false);

        rcEjercicios = view.findViewById(R.id.rcEjercicios);
        lnCargando= view.findViewById(R.id.content_loading);
        lnSinEntrenamiento= view.findViewById(R.id.lnSinEntrenamiento);


        RecyclerView.LayoutManager lManager = new LinearLayoutManager(this.getContext());
        rcEjercicios.setLayoutManager(lManager);
        rcEjercicios.setHasFixedSize(true);
        servicioWeb = ServicioWebUtils.getServicioWeb(true);
        scrollView=view.findViewById(R.id.scrollView);

        btnGenerarPlan=view.findViewById(R.id.btnGenerarPlan);
        pgbGenerandoPlan=view.findViewById(R.id.pgbGenerandoPlan);

        lnGenerarPlan=view.findViewById(R.id.lnGenerarPlan);


        ejercicios= new ArrayList<>();
        mAdapter = new EjerciciosAdapter(ejercicios, this.getContext());
        rcEjercicios.setItemAnimator(new DefaultItemAnimator());
        rcEjercicios.setAdapter(mAdapter);



        textView= view.findViewById(R.id.tv);
        tvRecomendaciones=view.findViewById(R.id.tvRecomendaciones);

        tabHost=  view.findViewById(android.R.id.tabhost);
        tabHost.setup(getContext(),getFragmentManager(), android.R.id.tabcontent);


        tabHost.setOnTabChangedListener(this);
        btnGenerarPlan.setOnClickListener(this);
        asignarTabs();



        // plan=MainActivity.planAlimentacion;



        if(plan==null){
            getPlan();
        }else{
            textView.setText("Semana: "+plan.getSemana_actual()+"  Día: "+plan.getDias_transcurridos());

            getDiaActual();


        }


        return  view;
    }
    void  asignarTabs(){

        for(int i =0; i<dias.length;i++)
        {
            this.setNewTab(getContext(), tabHost, dias[i],   R.id.tab1);

        }

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {

            TextView tv =  tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorW));
        }
        tabHost.setCurrentTab(0);




        //getDiaActual();


    }
    private void getPlan(){

        if(MainActivity.deportista!=null){



            lnCargando.setVisibility(View.VISIBLE);
            tabHost.setVisibility(View.GONE);
            Call<ResServer> resServerCall = servicioWeb.getMisPlanes(MainActivity.deportista.getId() );

            resServerCall.enqueue(new Callback<ResServer>() {
                @Override
                public void onResponse(Call<ResServer> call, Response<ResServer> response) {
                    if ( response.isSuccessful() ) {
                        ResServer resServer= response.body();
                        lnCargando.setVisibility(View.GONE);

                        if ( resServer != null ) {

                            if ( resServer.isOkay() ) {

                                ArrayList<Plan> planes  = resServer.getRespuesta().getPlanes();

                                if(planes.size()>0)
                                {
                                    lnGenerarPlan.setVisibility(View.GONE);

                                    for (Plan plan : planes) {

                                        if(plan.getTipo().equals("ENTRENAMIENTO")){

                                            EjerciciosFragment.plan=plan;

                                            textView.setText("Semana: "+plan.getSemana_actual()+"  Día: "+plan.getDias_transcurridos());
                                            getDiaActual();
                                        }

                                    }
                                    tabHost.setVisibility(View.VISIBLE);

                                }
                                else{
                                    lnGenerarPlan.setVisibility(View.VISIBLE);
                                }






                            } else {

                                Toast.makeText(getContext(), resServer.getMensaje(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResServer> call, Throwable t) {
                    t.printStackTrace();
                    lnCargando.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Ocurrió un inconveniente revise su conexió, intente de nuevo", Toast.LENGTH_SHORT).show();
                }
            });




        }


    }
    void getDiaActual()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int day_select=0;

        switch (day) {

            case Calendar.MONDAY:
                day_select=0;
                tabHost.setCurrentTab(0);
                break;
            case Calendar.TUESDAY:
                day_select=1;
                tabHost.setCurrentTab(1);
                break;
            case Calendar.WEDNESDAY:
                day_select=2;
                tabHost.setCurrentTab(2);
                break;
            case Calendar.THURSDAY:
                day_select=3;
                tabHost.setCurrentTab(3);
                break;

            case Calendar.FRIDAY:
                day_select=4;
                tabHost.setCurrentTab(4);
                break;
            case Calendar.SATURDAY:
                day_select=5;
                tabHost.setCurrentTab(5);
                break;
            case Calendar.SUNDAY:
                day_select=6;
                tabHost.setCurrentTab(6);
                break;
        }


        TextView tv =  tabHost.getTabWidget().getChildAt(day_select).findViewById(android.R.id.title);

        tv.setText("HOY");

        final int finalDay_select = day_select;
        scrollView.post(new Runnable() {
            @Override
            public void run() {

                scrollView.scrollTo(tabHost.getTabWidget().getChildAt(finalDay_select).getLeft(), 0);
            }
        });

    }

    private void setNewTab(Context context, FragmentTabHost tabHost, String title,  int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(title);
        tabSpec.setIndicator(title); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }
    ArrayList<Ejercicio>getEjercicios(String _dia){

        if(plan!=null){


            if(plan.getDias()!=null){
                for (Dia dia : plan.getDias()) {

                    if(dia.getDia().equals(_dia)){
                        if(!dia.getRecomendaciones().isEmpty())
                        {
                            tvRecomendaciones.setText(dia.getRecomendaciones());
                            tvRecomendaciones.setVisibility(View.VISIBLE);
                        }else{
                            tvRecomendaciones.setVisibility(View.GONE);

                        }

                        return  dia.getEjercicios();
                    }

                }
            }

        }

        return  new ArrayList<>();

    }

    @Override
    public void onTabChanged(String tabId) {

        ejercicios= getEjercicios(tabId.toUpperCase());


        if(ejercicios.size()>0){
            mAdapter.setItems(ejercicios);
            rcEjercicios.setVisibility(View.VISIBLE);
            lnSinEntrenamiento.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }else{
            rcEjercicios.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            tvRecomendaciones.setVisibility(View.GONE);

            lnSinEntrenamiento.setVisibility(View.VISIBLE);



        }



    }


    void generarPlan()
    {
        if(MainActivity.deportista!=null){


            pgbGenerandoPlan.setVisibility(View.VISIBLE);
            btnGenerarPlan.setVisibility(View.GONE);
            Call<ResServer> resServerCall = servicioWeb.generarPlan(MainActivity.deportista.getId() );

            resServerCall.enqueue(new Callback<ResServer>() {
                @Override
                public void onResponse(Call<ResServer> call, Response<ResServer> response) {
                    if ( response.isSuccessful() ) {
                        ResServer resServer= response.body();
                        pgbGenerandoPlan.setVisibility(View.GONE);

                        if ( resServer != null ) {

                            if ( resServer.isOkay() ) {





                                Toast.makeText(getContext(), "Plan generado exitosamente", Toast.LENGTH_SHORT).show();
                                getPlan();



                            } else {

                                Toast.makeText(getContext(), resServer.getMensaje(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResServer> call, Throwable t) {
                    t.printStackTrace();
                    pgbGenerandoPlan.setVisibility(View.GONE);
                    btnGenerarPlan.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Ocurrió un inconveniente revise su conexió, intente de nuevo", Toast.LENGTH_SHORT).show();
                }
            });




        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGenerarPlan:

                generarPlan();


                break;
        }
    }
}
