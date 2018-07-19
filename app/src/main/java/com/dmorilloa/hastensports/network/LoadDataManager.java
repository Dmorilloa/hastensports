package com.dmorilloa.hastensports.network;

import android.util.Log;

import com.dmorilloa.hastensports.item.Sport;
import com.dmorilloa.hastensports.listener.LoadDataListener;
import com.dmorilloa.hastensports.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase utilizada para gestionar la descarga de la información
 * Created by Diego Morillo on 18/07/2018.
 */

public class LoadDataManager {

    /**
     * Elemento utilizado para acceder al API REST
     */
    private Retrofit mRetrofit;

    /**
     * Constructor de la clase. Se indica que se obtiene como dependencia
     * @param retrofit Elemento para acceder al API REST
     */
    @Inject
    public LoadDataManager(Retrofit retrofit){
        mRetrofit=retrofit;
    }

    /**
     * Método utilizado para cargar los datos
     * @param listener Elemento que esta a la espera de recibir los datos cargados
     */
    public void loadData(final LoadDataListener listener){
        if(listener!=null) {
            RestClient restClient = mRetrofit.create(RestClient.class);
            Call<List<Sport>> call = restClient.getData();

            call.enqueue(new Callback<List<Sport>>() {
                @Override
                public void onResponse(Call<List<Sport>> call, Response<List<Sport>> response) {
                    switch (response.code()) {
                        case 200:
                            listener.onDataLoaded(response.body());
                            break;
                        default:
                            listener.onDataLoadError();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<Sport>> call, Throwable t) {
                    Log.e("error", t.toString());
                    listener.onDataLoadError();
                }
            });
        }

    }
}
