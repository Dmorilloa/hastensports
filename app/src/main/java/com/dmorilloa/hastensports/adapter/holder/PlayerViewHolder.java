package com.dmorilloa.hastensports.adapter.holder;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.dmorilloa.hastensports.R;
import com.dmorilloa.hastensports.item.Player;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Clase que gestiona la vista que representa a los jugadores en la lista extensible. Hereda de
 * {@link ChildViewHolder} para poder utilizarlo en la lista extensible con recyclerview.
 * Created by Diego Morillo on 18/07/2018.
 */
public class PlayerViewHolder extends ChildViewHolder {

    /**
     * Vistas donde se muestra la imagen del jugador
     */
    ImageView mImage;

    /**
     * Vista donde se muestra el nombre del jugador
     */
    TextView mName;

    /**
     * Vista donde se muestra el apellido del jugador
     */
    TextView mSurname;

    /**
     * Vista donde se muestra la fecha de nacimiento del jugador
     */
    TextView mDate;

    /**
     * Elemento utilizado para poder solicitar las imágenes desde su url
     */
    RequestQueue mQueue;

    /**
     * Constructor de la clase. Se encarga de obtener las vistas que se van a utilizar
     * @param itemView Vista principal que representa al jugador
     * @param queue Elemento para solicitar las imágenes
     */
    public PlayerViewHolder(View itemView,RequestQueue queue) {
        super(itemView);
        mImage=(ImageView) itemView.findViewById(R.id.item_list_image);
        mName=(TextView)itemView.findViewById(R.id.item_list_name);
        mSurname=(TextView) itemView.findViewById(R.id.item_list_surname);
        mDate=(TextView) itemView.findViewById(R.id.item_list_date);
        this.mQueue=queue;
    }

    /**
     * Método utilizado para dar valor a las distintas vistas en función de la información del jugador
     * @param player Información del jugador
     */
    public void onBind(Player player) {
        int noImage=R.drawable.no_image_player;
        //Se solicita la imagen. El valor utilizado para identificar la petición, se crea en base
        //a la información del jugador
        showImage(mImage,player.getImage(),noImage,player.getName()+player.getSurname());
        mName.setText(player.getName());
        mSurname.setText(player.getSurname());
        mDate.setText(player.getDate());
    }

    /**
     * Método utilizado para obtener la imagen que se tiene que mostrar
     * @param imageView Vista donde se mostrará la imagen
     * @param url Url donde se encuentra la imagen
     * @param resourceNoImage Imagen mostrada por defecto mientras se carga la imagen. Si no se puede
     *                        descargar la imagen, será la imagen que se muestra finalmente.
     * @param tag Valor utilizado para identificar esta petición
     */
    private void showImage(final ImageView imageView,String url,int resourceNoImage,String tag){
        //Si la vista tiene indicada una etiqueta, es que se esta reutilizando. En este caso se
        //cancela la petición para la imagen en su anterior uso.
        if(imageView.getTag()!=null) {
            mQueue.cancelAll(imageView.getTag());
        }

        //Se muestra la imagen por defecto
        imageView.setImageResource(resourceNoImage);
        @SuppressWarnings("deprecation")
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //imageView.setImageResource());
                    }
                });
        //Se indica el identificador de la petición y se guarda dicho identificador como etiqueta
        //en la vista, para poder conocer después la petición asociada a ella
        request.setTag(tag);
        imageView.setTag(tag);

        mQueue.add(request);
    }
}
