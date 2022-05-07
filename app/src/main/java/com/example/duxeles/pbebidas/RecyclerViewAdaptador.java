package com.example.duxeles.pbebidas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.example.duxeles.R;//<-----------------


public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.bebidaViewHolder> {

    public static class bebidaViewHolder extends RecyclerView.ViewHolder{

        private TextView nom, precio, desc;
        ImageView img;

        public bebidaViewHolder(View itemView) {
            super(itemView);

            nom=(TextView)itemView.findViewById(R.id.nom);
            precio=(TextView)itemView.findViewById(R.id.precio);
            desc=(TextView)itemView.findViewById(R.id.desc);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }

    //Variable para almacenar los datos
    public List<bebidas> bebidasLista;

    public RecyclerViewAdaptador(List<bebidas> bebidasLista) {
        this.bebidasLista = bebidasLista;
    }

    //Agrega un nuevo item a la lista haciendo uso de un layout dentro de otro layout
    @NonNull
    @Override
    public bebidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articulo,null,false);
        return new bebidaViewHolder(view);
    }


    //Realiza las modificaciones del contenido para cada item
    @Override
    public void onBindViewHolder(bebidaViewHolder holder, int position) {
        holder.nom.setText(bebidasLista.get(position).getNom());
        holder.precio.setText(String.valueOf(bebidasLista.get(position).getPrecio()));
        holder.desc.setText(bebidasLista.get(position).getDesc());
        //holder.img.setImageResource(); //foto?
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(bebidasLista.get(position).getImg(),0,bebidasLista.get(position).getImg().length));
    }

    //Permite determinar al adaptador la cantidad de elementos que se van a procesar
    @Override
    public int getItemCount() {
        return bebidasLista.size();
    }
}
