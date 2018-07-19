package com.dmorilloa.hastensports;

import android.app.Application;

import com.dmorilloa.hastensports.di.component.DaggerAppComponent;
import com.dmorilloa.hastensports.di.component.AppComponent;
import com.dmorilloa.hastensports.di.module.AppModule;

/**
 * Clase que representa el estado de la aplicación
 * Created by Diego Morillo on 18/07/2018.
 */

public class App extends Application {

    /**
     * Componente para obtener las dependencias
     */
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    /**
     * Método utilizado para inicializar las dependencias
     */
    private void initializeInjector(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * Método utilizado para obtener el elemento utilizado para obtener las dependencias
     * @return Componente con las dependencias
     */
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
