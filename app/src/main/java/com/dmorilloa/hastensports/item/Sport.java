
package com.dmorilloa.hastensports.item;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
/**
 * Clase que engloba la información de un deporte. Hereda {@link ExpandableGroup} para poder ser
 * utilizado en la lista extensible con recyclerView
 * Created by Diego Morillo on 17/07/2018.
 */
public class Sport extends ExpandableGroup<Player> {

    /**
     * Lista con los jugadores de este deporte
     */
    @Valid
    private List<Player> players = null;

    /**
     * Tipo de deporte
     */
    private String type;

    /**
     * Constructor sin parámetros
     * 
     */
    public Sport() {
        super("",new ArrayList<Player>());
    }

    /**
     * Constructor con los parámetros del deporte
     * @param title Nombre del deporte
     * @param players Información de los jugadores de este deporte
     * @param type Tipo de deporte
     */
    public Sport(List<Player> players, String type, String title) {
        super(title,players);
        this.players = players;
        this.type = type;
    }

    /**
     * Método utilizado para obtener los jugadores de este deporte
     * @return Jugadores de este deporte
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Método utilizado para fijar los jugadores de este deporte
     * @param players Jugadores de este deporte
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Método utilizado para obtener el tipo de deporte
     * @return Tipo de deporte
     */
    public String getType() {
        return type;
    }

    /**
     * Método utilizado para fijar el tipo de deporte
     * @param type Tipo de deporte
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<Player> getItems() {
        return players;
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

}
