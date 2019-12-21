package com.unicordoba.agarf.activitys;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.basedatos.DBManager;
import com.unicordoba.agarf.fragments.DatePickerFragment;
import com.unicordoba.agarf.modelos.Deportista;
import com.unicordoba.agarf.servicios.ResServer;
import com.unicordoba.agarf.servicios.ServicioWeb;
import com.unicordoba.agarf.servicios.ServicioWebUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText edtIdentificacion, edtNombres, edtApellidos, edtFechaNacimiento, edtEstatura, edtPass;
    Spinner spSexo;
    private Button btnRegistrar;
    private ProgressBar progressRegistro;

    private DBManager dbManager;
    private ServicioWeb servicioWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("AGARF Registro");

        edtIdentificacion = findViewById(R.id.edtIdentificacion);
        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtFechaNacimiento = findViewById(R.id.edtFechaNacimiento);
        edtEstatura = findViewById(R.id.edtEstatura);
        spSexo = findViewById(R.id.spnSexo);
        edtPass = findViewById(R.id.edtContraseña);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        progressRegistro = findViewById(R.id.progressRegistroCliente);

        btnRegistrar.setOnClickListener(this);
        edtFechaNacimiento.setOnClickListener(this);
        dbManager = new DBManager(this);

        servicioWeb = ServicioWebUtils.getServicioWeb(true);
    }


    private void registrar(){
        progressRegistro.setVisibility(View.VISIBLE);
        btnRegistrar.setVisibility(View.GONE);

        Deportista deportista = new Deportista();
        deportista.setId(Integer.parseInt(edtIdentificacion.getText().toString()));
        deportista.setPass(edtPass.getText().toString());
        deportista.setNombres(edtNombres.getText().toString());
        deportista.setApellidos(edtApellidos.getText().toString());
        deportista.setFecha_nacimiento(edtFechaNacimiento.getText().toString());
        deportista.setEstatura(Integer.parseInt(edtEstatura.getText().toString()));
        deportista.setSexo(spSexo.getSelectedItem().toString());



        Call<ResServer> resServerCall = servicioWeb.deportistasRegistro(deportista);

        resServerCall.enqueue(new Callback<ResServer>() {
            @Override
            public void onResponse(Call<ResServer> call, Response<ResServer> response) {
                progressRegistro.setVisibility(View.GONE);
                btnRegistrar.setVisibility(View.VISIBLE);

                if (response.isSuccessful()){
                    ResServer resServer = response.body();

                    if (resServer != null){
                        if (resServer.isOkay()){
                             Toast.makeText(RegistroActivity.this, "Deportista registrado satisfactoriamente, por favor loguearse", Toast.LENGTH_SHORT).show();
                        }else {
                            progressRegistro.setVisibility(View.GONE);
                            Toast.makeText(RegistroActivity.this, "Deportista no pudo ser registrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResServer> call, Throwable t) {
                t.printStackTrace();
                progressRegistro.setVisibility(View.GONE);
                btnRegistrar.setVisibility(View.VISIBLE);
                Toast.makeText(RegistroActivity.this, "Ocurrió un inconveniente al registrar el Deportista.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validarCampos(){

        if (edtIdentificacion.getText().toString().trim().isEmpty()){
            edtIdentificacion.setError("Digite su identificación");
            edtIdentificacion.requestFocus();
            return false;
        }
        if (edtNombres.getText().toString().trim().isEmpty()){
            edtNombres.setError("Digite los nombres");
            edtNombres.requestFocus();
            return false;
        }
        if (edtApellidos.getText().toString().trim().isEmpty()){
            edtApellidos.setError("Digite los apellidos");
            edtApellidos.requestFocus();
            return false;
        }
        if (edtFechaNacimiento.getText().toString().trim().isEmpty()){
            edtFechaNacimiento.setError("Digite su fecha de nacimiento");
            edtFechaNacimiento.requestFocus();
            return false;
        }
        if (edtEstatura.getText().toString().trim().isEmpty()){
            edtEstatura.setError("Digite su estatura");
            edtEstatura.requestFocus();
            return false;
        }
        if (edtPass.getText().toString().trim().isEmpty()){
            edtPass.setError("Digite su contraseña");
            edtPass.requestFocus();
            return false;
        }

        return true;
    }


    //LAMA UN MODAL PARA SELECCIONAR LA FECHA

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = year + "-" + twoDigits(month+1) + "-" + twoDigits(day);

                edtFechaNacimiento.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:
                if (validarCampos()){
                    registrar();
                }

                break;
            case R.id.edtFechaNacimiento:
                showDatePickerDialog();
                break;
        }
    }
}
