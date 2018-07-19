package com.dmorilloa.hastensports.di.component;

import com.dmorilloa.hastensports.di.module.AppModule;
import com.dmorilloa.hastensports.gui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Clase que define el componente utilizado para acceder a las dependencias desde una activity
 * Created by Diego Morillo on 18/07/2018.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
