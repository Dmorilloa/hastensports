package com.dmorilloa.hastensports.gui;

import android.app.Activity;

import com.dmorilloa.hastensports.App;
import com.dmorilloa.hastensports.di.component.AppComponent;

/**
 * Clase utilizada para definir un método que debe utilizarse desde las distintas actividades de la
 * aplicación
 * Created by Diego Morillo on 18/07/2018.
 */
public class BaseActivity extends Activity{

    /**
     * Método utilizado para poder obtener las dependencias
     * @return Elemento para acceder a las dependencias
     */
    public AppComponent getAppComponent(){
        return ((App)getApplication()).getAppComponent();
    }
}
