package com.unicordoba.agarf.servicios;



import com.unicordoba.agarf.modelos.Deportista;
import com.unicordoba.agarf.utils.Constantes;

import retrofit2.Call;
import retrofit2.http.Body;
 import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioWeb {



        @FormUrlEncoded
        @POST(Constantes.URLs.LOGIN)
        Call<ResServer> login(@Field("id") int id, @Field("pass") String pass);


         @POST(Constantes.URLs.DEPORTISTAS_REGISTRO)
         Call<ResServer> deportistasRegistro(  @Body Deportista deportista);


         @GET(Constantes.URLs.PLANES_MIS_PLANES)
         Call<ResServer> getMisPlanes(@Path("id") int id);


        @GET(Constantes.URLs.PLANES_GENERAR)
        Call<ResServer> generarPlan(@Path("id") int id);

}


