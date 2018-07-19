package com.dmorilloa.hastensports.network;

import com.dmorilloa.hastensports.item.Sport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Clase que define las llamadas que se realizan al API REST
 * Created by Diego Morillo on 17/07/2018.
 */

public interface RestClient {
    /**
     * Método utilizado para realizar la llamada GET para cargar la información de los diferentes
     * deportes
     * @return Respuesta con la información de los deportes
     */
    @GET("66851")
    Call<List<Sport>> getData();
}
