package com.dmorilloa.hastensports.listener;

import com.dmorilloa.hastensports.item.Sport;

import java.util.List;

/**
 * Interfaz utilizad para informar sobre el estado de la carga de la información
 * Created by Diego Morillo on 18/07/2018.
 */
public interface LoadDataListener {
    /**
     * Método utilizado para indicar la información cargada
     * @param sports Información cargada
     */
    public void onDataLoaded(List<Sport> sports);

    /**
     * Método utilizado para indicar un error en la carga
     */
    public void onDataLoadError();
}
