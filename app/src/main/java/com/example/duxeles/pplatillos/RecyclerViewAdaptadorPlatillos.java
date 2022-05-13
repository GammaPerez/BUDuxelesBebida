package com.example.duxeles.pplatillos;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duxeles.R;

import java.util.List;


public class RecyclerViewAdaptadorPlatillos extends RecyclerView.Adapter<RecyclerViewAdaptadorPlatillos.platilloViewHolder> {

    public static class platilloViewHolder extends RecyclerView.ViewHolder{

        private TextView nom, precio, ing, desc;
        ImageView img;

        public platilloViewHolder (View itemView) {
            super(itemView);

            nom=(TextView)itemView.findViewById(R.id.nom);
            precio=(TextView)itemView.findViewById(R.id.signo);
            ing=(TextView)itemView.findViewById(R.id.ing);
            desc=(TextView)itemView.findViewById(R.id.desc);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }

    //Variable para almacenar los datos
    public List<platillo> platillosLista;

    public RecyclerViewAdaptadorPlatillos(List<platillo> platillosLista) {
        this.platillosLista = platillosLista;
    }

    //Agrega un nuevo item a la lista haciendo uso de un layout dentro de otro layout
    @NonNull
    @Override
    public platilloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platillo,null,false);
        return new platilloViewHolder(view);
    }


    //Realiza las modificaciones del contenido para cada item
    @Override
    public void onBindViewHolder(platilloViewHolder holder, int position) {
        holder.nom.setText(platillosLista.get(position).getNom());
        holder.precio.setText(String.valueOf(platillosLista.get(position).getPrecio()));
        holder.desc.setText(platillosLista.get(position).getDesc());
        holder.ing.setText(platillosLista.get(position).getIng());
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(platillosLista.get(position).getImg(),0,platillosLista.get(position).getImg().length));
    }

    //Permite determinar al adaptador la cantidad de elementos que se van a procesar
    @Override
    public int getItemCount() {
        return platillosLista.size();
    }
}
