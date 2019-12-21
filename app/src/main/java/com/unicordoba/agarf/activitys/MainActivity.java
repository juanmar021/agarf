package com.unicordoba.agarf.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.adapters.EjerciciosAdapter;
import com.unicordoba.agarf.basedatos.DBHelper;
import com.unicordoba.agarf.basedatos.DBManager;
import com.unicordoba.agarf.fragments.AlimentosFragment;
import com.unicordoba.agarf.fragments.EjerciciosFragment;
import com.unicordoba.agarf.fragments.MisDatosFragment;
import com.unicordoba.agarf.interfaces.IFragments;
import com.unicordoba.agarf.modelos.Deportista;
import com.unicordoba.agarf.modelos.Plan;
import com.unicordoba.agarf.servicios.ServicioWeb;
import com.unicordoba.agarf.servicios.ServicioWebUtils;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity  implements  IFragments  {

    private android.view.Menu menu;
    DBManager dbManager;
    private ServicioWeb servicioWeb;
    private AlertDialog alert;
    private FragmentTabHost tabHost;
    private  Toolbar myToolbar;
    private LinearLayout lnCargando;
    private LinearLayout lnMain;
    public  static  Deportista deportista= new Deportista();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar =   findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setTitleTextColor( this.getResources().getColor(R.color.colorPrimary));
        lnCargando= findViewById(R.id.content_loading);
        lnMain=findViewById(R.id.content_fragment);



        dbManager = new DBManager(this);
        deportista=dbManager.getDeportista();
        servicioWeb = ServicioWebUtils.getServicioWeb(true);

        tabHost=  findViewById(android.R.id.tabhost);

//
      tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setVisibility(View.VISIBLE);
                myToolbar.setTitle(R.string.app_name);
            }
        });



        cargarTabs();



    }

    private  void cargarTabs(){
        this.setNewTab(this, tabHost, "tab2", R.string.ejercicios,  R.drawable.ic_ejercicio, EjerciciosFragment.class);

        this.setNewTab(this, tabHost, "tab1", R.string.alimentos, R.drawable.ic_foods,AlimentosFragment.class);


    }


    private void setNewTab(Context context, FragmentTabHost tabHost, String tag, int title, int icon, Class<?> clss){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
         tabHost.addTab(tabSpec,clss,null) ;
}

    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv =  view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
       // TextView tv =  view.findViewById(R.id.textView);
     //   tv.setText(title);
        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_logout){

            Logout();

        }
        else if(item.getItemId()==R.id.action_mis_datos){

            myToolbar.setTitle("Mis datos");


            Fragment miFragment = new MisDatosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,miFragment).commit();
            tabHost.setVisibility(View.GONE);

        }

        return super.onOptionsItemSelected(item);
    }

    void Logout(){


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Seguro que desea cerrar sesión?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        if (dbManager.borrarDatosTabla(DBHelper.TABLA_DEPORSTISTAS) > 0) {
                            AlimentosFragment.plan=null;
                            EjerciciosFragment.plan=null;

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(MainActivity.this, "No se pudo cerrar la sesión ", Toast.LENGTH_SHORT);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();

                    }
                });
        alert = builder.create();
        alert.show();

    }


}
