package com.dmorilloa.hastensports.di.module;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dmorilloa.hastensports.App;
import com.dmorilloa.hastensports.adapter.ExpandableSportAdapter;
import com.dmorilloa.hastensports.item.Sport;
import com.dmorilloa.hastensports.network.LoadDataManager;
import com.dmorilloa.hastensports.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase que define el modulo que provee las distintas dependencias.
 * Created by Diego Morillo on 18/07/2018.
 */
@Module
public class AppModule {

    /**
     * Referencia a la aplicación
     */
    private final App mApplication;

    /**
     * Constructor de la clase
     * @param application Referencia a la aplicación
     */
    public AppModule(App application){
        this.mApplication=application;
    }

    /**
     * Método utilizado para poder obtener el contexto de la aplicación como una dependencia
     * @return Contexto de la aplicación
     */
    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

    /**
     * Método utilizado para poder obtener como dependencia el elemento que se utiliza para descargar
     * imágenes desde internet en tiempo de ejecución
     * @param context Contexto de la aplicación
     * @return Elemento para solicitar las imágenes
     */
    @Provides
    @Singleton
    RequestQueue provideRequestQueue(Context context){
        return Volley.newRequestQueue(context);
    }

    /**
     * Método utilizado para poder obtener como dependencia el elemento utilizado para descargar
     * la información que se tiene que mostrar en la aplicación
     * @param retrofit Elemento utilizado para acceder al API Rest
     * @return Elemento utilizado para descargar la información
     */
    @Provides
    @Singleton
    LoadDataManager provideLoadDataManager(Retrofit retrofit){
        return new LoadDataManager(retrofit);
    }

    /**
     * Método utilizado para obtener como dependencia el elemento utilizado para parsear json
     * @return Elemento utilizado para parsear json
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    /**
     * Método utilizado para obtener como dependencia el elemento utilizado para utilizar el API REST
     * @param gson Elemento utilizado para parsear json
     * @return Elemento para acceder al api rest
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.URL_DATA)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Método utilizado para obtener como dependencia la clase que gestiona la información que se
     * muestra en la lista
     * @param queue Elemento para obtener las imagenes desde su url
     * @return Adaptador para gestionar como se muestra la información en la lista
     */
    @Provides
    ExpandableSportAdapter provideExpandableSportAdater(RequestQueue queue){
        return new ExpandableSportAdapter(queue,new ArrayList<Sport>());
    }
}
