package com.dmorilloa.hastensports.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.dmorilloa.hastensports.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Clase que gestiona a la vista que representa los deporte en la lista extensible. Hereda de
 * {@link GroupViewHolder} para poder utilizarlo en la lista extensible con recyclerview.
 * Created by Diego Morillo on 18/07/2018.
 */
public class SportViewHolder extends GroupViewHolder {

    /**
     * Vista donde se muestra el nombre del deporte
     */
    private TextView mTitle;

    /**
     * Constructor de la clase
     * @param itemView Vista principal que representa el deporte
     */
    public SportViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.item_spinner_text);
    }

    /**
     * Método utilizado para dar valor al nombre del deporte
     * @param title Nombre del deporte
     */
    public void setTitle(String title) {
            mTitle.setText(title);
        }
}
