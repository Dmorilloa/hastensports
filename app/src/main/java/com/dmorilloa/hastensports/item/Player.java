
package com.dmorilloa.hastensports.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase que engloba la información de un jugador. Implementa {@link Parcelable} para poder ser
 * utilizado en la lista extensible que utiliza recyclerView
 * Created by Diego Morillo on 17/07/2018.
 */
public class Player implements Parcelable {

    /**
     * Url de la imagen del jugador
     */
    private String mImage;

    /**
     * Apellido del jugador
     */
    private String mSurname;

    /**
     * Nombre del jugador
     */
    private String mName;

    /**
     * Fecha de nacimiento del jugador
     */
    private String mDate;


    public static final Parcelable.Creator<Player> CREATOR
            = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    /**
     * Constructor sin parametros
     * 
     */
    public Player() {
    }

    /**
     * Constructor con los distintos parametros del jugador
     * @param name Nombre del jugador
     * @param surname Apellido del jugador
     * @param image Url con la imagen del jugador
     * @param date Fecha de nacimiento del jugador
     */
    public Player(String image, String surname, String name, String date) {
        super();
        this.mImage = image;
        this.mSurname = surname;
        this.mName = name;
        this.mDate = date;
    }

    /**
     * Constructor a partir del contenedor
     * @param in Contenedor con la información del jugador
     */
    public Player(Parcel in){
        readFromParcel(in);
    }

    /**
     * Método utilizado para obtener la url de la imagen del jugaodor
     * @return Url con la imagen del jugador
     */
    public String getImage() {
        return mImage;
    }

    /**
     * Método utilizado para fijar la url de la imagen del jugador
     * @param image Url con la imagen del jugador
     */
    public void setImage(String image) {
        this.mImage = image;
    }

    /**
     * Método utilizado para obtener el apellido del jugador
     * @return Apellido del jugador
     */
    public String getSurname() {
        return mSurname;
    }

    /**
     * Método utilizado para fijar el apellido del jugador
     * @param surname Apellido del jugador
     */
    public void setSurname(String surname) {
        this.mSurname = surname;
    }

    /**
     * Método utilizado para obtener el nombre del jugador
     * @return Nombre del jugador
     */
    public String getName() {
        return mName;
    }

    /**
     * Método utilizado para fijar el nombre del jugador
     * @param name Nombre del jugador
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Método utilizado para obtener la fecha de nacimiento del jugador
     * @return Fecha de nacimiento del jugador
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Método utilizado para fijar la fecha de nacimiento del jugador
     * @param date Fecha de nacimiento del jugador
     */
    public void setDate(String date) {
        this.mDate = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mImage);
        dest.writeString(this.mSurname);
        dest.writeString(this.mName);
        dest.writeString(this.mDate);
    }

    private void readFromParcel(Parcel in) {
        this.mImage =in.readString();
        this.mSurname =in.readString();
        this.mName =in.readString();
        this.mDate =in.readString();
    }
}
