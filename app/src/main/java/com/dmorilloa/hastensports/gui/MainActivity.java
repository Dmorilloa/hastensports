package com.dmorilloa.hastensports.gui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dmorilloa.hastensports.R;
import com.dmorilloa.hastensports.adapter.ExpandableSportAdapter;
import com.dmorilloa.hastensports.item.Sport;
import com.dmorilloa.hastensports.listener.LoadDataListener;
import com.dmorilloa.hastensports.network.LoadDataManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
/**
 * Clase que gestiona la activitidad principal de la aplicación
 * Created by Diego Morillo on 17/07/2018.
 */
public class MainActivity extends BaseActivity implements LoadDataListener {

    /**
     * Vista que muestra la lista de deportistas
     */
    private RecyclerView mList;

    /**
     * Clase utilizada para gestionar como se muestra la información en la lista. En esta caso una
     * lista extensible que muestra los deportistas ordenados en su determinado deporte
     */
    @Inject
    ExpandableSportAdapter mAdapter;

    /**
     * Diálogo utilizado para indicar que se está descargando la información
     */
    private ProgressDialog mProgress;

    /**
     * Elemento utilizado para obtener la información que se tiene que mostrar
     */
    @Inject
    LoadDataManager mDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se incluye en las dependencias
        getAppComponent().inject(this);

        //Se carga y define la lista
        this.mList=(RecyclerView)this.findViewById(R.id.list);
        this.mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);

        //Se inicia la carga de los datos
        showDialog(true);
        mDataManager.loadData(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        //Si se estaba mostrando el dialogo, se cierra
        showDialog(false);
    }

    /**
     * Método utilizado cuando se finaliza de cargar la información
     * @param sports Información cargada
     */
    public void onDataLoaded(List<Sport> sports){
        //Se actualiza el adapter y se oculta el dialogo
        mAdapter.setGroups(sports);
        showDialog(false);

    }

    /**
     * Método ejecutado cuando no se puede cargar la información
     */
    public void onDataLoadError(){
        showDialog(false);
    }

    /**
     * Método utilizado para gestionar la visibilidad del dialogo
     * @param show Indica si se debe mostrar u ocultar el dialogo
     */
    private void showDialog(boolean show){
        if(show){
            if(mProgress==null) {
                mProgress = new ProgressDialog(this);
                mProgress.setMessage(this.getResources().getString(R.string.loading_data));
                mProgress.show();
            }
        }else{
            if(mProgress!=null){
                mProgress.dismiss();
                mProgress=null;
            }
        }
    }

}
