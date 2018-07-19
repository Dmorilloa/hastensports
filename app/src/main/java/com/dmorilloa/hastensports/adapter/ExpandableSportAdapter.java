package com.dmorilloa.hastensports.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.dmorilloa.hastensports.R;
import com.dmorilloa.hastensports.adapter.holder.PlayerViewHolder;
import com.dmorilloa.hastensports.adapter.holder.SportViewHolder;
import com.dmorilloa.hastensports.item.Player;
import com.dmorilloa.hastensports.item.Sport;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Clase utilizada para gestionar el contenido de la lista extensible. Utilizo una librería open source
 * para la creación de ExpandableListView que utilizan RecyclerView. Esta clase hereda de
 * {@link ExpandableRecyclerViewAdapter} para conseguir dicho resultado. Se le pasan como parámetros
 * las clases que gestionan las vistas que representan las distintas secciones (deportes) y su contenido
 * (jugadores)
 * Created by Diego Morillo on 18/07/2018.
 */

public class ExpandableSportAdapter extends ExpandableRecyclerViewAdapter<SportViewHolder,PlayerViewHolder> {

    /**
     * Elemento utilizado para solicitar las imagenes en base a su url
     */
    RequestQueue mQueue;

    /**
     * Constructor de la clase.
     * @param queue Elemento para solicitar las imagenes
     * @param sports Lista con los información que mostrar en la lista inicialmente
     */
    public ExpandableSportAdapter(RequestQueue queue,List<Sport> sports) {
        super(sports);
        this.mQueue = queue;
    }

    /**
     * Método utilizado para obtener la clase que gestiona la vista de las secciones de la lista
     * extensible
     * @param parent Vista padre para esta vista
     * @param viewType Tipo de vista
     * @return Clase que gestiona la vista de las secciones de la lista
     */
    @Override
    public SportViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sport, parent, false);
        return new SportViewHolder(view);
    }

    /**
     * Método utilizado para obtener la clase que gestiona la vista de los elementos en las distintas
     * secciones de la vista
     * @param parent Vista padre para este vista
     * @param viewType Tipo de vista
     * @return Clae que gestiona la vista de los elementos en las secciones de la lista
     */
    @Override
    public PlayerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_player, parent, false);
        return new PlayerViewHolder(view,mQueue);
    }

    /**
     * Método utilizado para dar valor a un elemento de una sección de la lista
     * @param holder Clase que gestiona la vista
     * @param flatPosition Posicion del elemento en la lista
     * @param group Información de la sección
     * @param childIndex Posición del elemento en la sección
     */
    @Override
    public void onBindChildViewHolder(PlayerViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final Player player = ((Sport) group).getItems().get(childIndex);
        holder.onBind(player);
    }

    /**
     * Método utilizado para dar valor a una sección de la lista
     * @param holder Clase que gestiona la vista
     * @param flatPosition Posición del elemento en la lista
     * @param group Información de la sección
     */
    @Override
    public void onBindGroupViewHolder(SportViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitle(group.getTitle());
    }

    /**
     * Método utilizado para actualizar la información que se muestra en la lista
     * @param sports Nueva información a mostrar en la lista
     */
    public void setGroups(List<Sport> sports){
        //Se almacenan los nuevos valores
        this.expandableList.groups=sports;
        this.expandableList.expandedGroupIndexes = new boolean[this.expandableList.groups.size()];
        for (int i = 0; i < this.expandableList.groups.size(); i++) {
            this.expandableList.expandedGroupIndexes[i] = false;
        }
        //Se notifican los cambios para que la lista se actualice
        notifyDataSetChanged();
    }

}
