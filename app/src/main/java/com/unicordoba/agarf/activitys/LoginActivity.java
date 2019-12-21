package com.unicordoba.agarf.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unicordoba.agarf.R;
import com.unicordoba.agarf.basedatos.DBManager;
import com.unicordoba.agarf.modelos.Deportista;
import com.unicordoba.agarf.servicios.ResServer;
import com.unicordoba.agarf.servicios.ServicioWeb;
import com.unicordoba.agarf.servicios.ServicioWebUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edtId,edtPass;
    private Button btnEntrar,btntoRegistro;
    private boolean visibilityPass;
    private LinearLayout lnBotones;
    private ProgressBar progress;
    private ServicioWeb servicioWeb;
    private DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("AGARF Iniciar sesión");

        dbManager = new DBManager(this);
        isLogin();
        btntoRegistro= findViewById(R.id.btnIrARegistro);

        edtId=findViewById(R.id.edtId);
        edtPass=findViewById(R.id.edtPass);
        btnEntrar= findViewById(R.id.btnEntrar);
        progress= findViewById(R.id.pgbLogin);
        lnBotones=findViewById(R.id.lnBotones);
        servicioWeb = ServicioWebUtils.getServicioWeb(true);

        visibilityPass=false;





        btnEntrar.setOnClickListener(this);
        btntoRegistro.setOnClickListener(this);

        visibilityPass();


        edtPass.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if ( validarCampos() ) {
                       // login();
                    }
                    return true;
                }
                return false;
            }
        });




    }

    private void isLogin()
    {
        Deportista deportista= dbManager.getDeportista();

        if(deportista!=null)
        {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }


    }
    private boolean validarCampos() {

        if ( edtId.getText().toString().trim().isEmpty() ) {
            edtId.setError("Digite su Identificación");
            edtId.requestFocus();

            return false;
        }

        if ( edtPass.getText().toString().trim().isEmpty() ) {
            edtPass.setError("Digite su contraseña");
            edtPass.requestFocus();

            return false;
        }

        return true;
    }

    void  login()
    {
        Log.v("Inicio sesion", "login");

        progress.setVisibility(View.VISIBLE);
        lnBotones.setVisibility(View.GONE);
        Call<ResServer> resServerCall = servicioWeb.login(
                Integer.parseInt(edtId.getText().toString()),
                edtPass.getText().toString().trim()
        );

        resServerCall.enqueue(new Callback<ResServer>() {
            @Override
            public void onResponse(Call<ResServer> call, Response<ResServer> response) {
                if ( response.isSuccessful() ) {
                    ResServer resServer= response.body();

                    if ( resServer != null ) {

                        if ( resServer.isOkay() ) {

                            Deportista deportista = resServer.getRespuesta().getDeportista();


                            long i=dbManager.insertarDeportista(deportista);
                            if(i>0)
                            {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else
                            {
                                Toast.makeText(getApplicationContext(), "No se pudo iniciar sesion intentelo de nuevo", Toast.LENGTH_SHORT).show();

                            }


                        } else {
                            progress.setVisibility(View.GONE);
                            lnBotones.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, resServer.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResServer> call, Throwable t) {
                t.printStackTrace();
                progress.setVisibility(View.GONE);
                lnBotones.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "Ocurrió un inconveniente revise su conexió, intente de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void visibilityPass()
    {
        edtPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edtPass.getRight() - edtPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        Drawable img;

                        if(visibilityPass)
                        {
                            img= getApplicationContext().getResources().getDrawable(R.drawable.ic_action_visibility);
                            edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        }
                        else
                        {
                            img= getApplicationContext().getResources().getDrawable(R.drawable.ic_action_visibility_off);
                            edtPass.setInputType(InputType.TYPE_CLASS_TEXT);



                        }
                        visibilityPass=!visibilityPass;
                        img.setBounds(edtPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds());
                        edtPass.setCompoundDrawables(edtPass.getCompoundDrawables()[0],null,img,null);


                        return true;
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())

        {
               case R.id.btnEntrar:

                if ( validarCampos() ) {
                    login();

                }


                break;

            case R.id.btnIrARegistro:

                 Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
                break;


        }

    }
}
