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
import com.example.duxeles.pbebidas.bebidas;

import java.util.ArrayList;

public class ListaPlatillosAdapter extends RecyclerView.Adapter<ListaPlatillosAdapter.ViewHolder> implements View.OnClickListener{

        ArrayList<platillo> listaPlatillos;
        private View.OnClickListener listener;

    public ListaPlatillosAdapter( ArrayList<platillo> listaPlatillos){
        this.listaPlatillos = listaPlatillos;
    }

    @NonNull
    @Override //Asigna el diseño de cada elemento de la lista
    public ListaPlatillosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platillo, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
          }

    @Override //Asigna los elementos correspondidentes para cada campo
    public void onBindViewHolder(ListaPlatillosAdapter.ViewHolder holder, int position) {
        holder.nom.setText(listaPlatillos.get(position).getNom());
        holder.precio.setText(String.valueOf(listaPlatillos.get(position).getPrecio()));
        holder.desc.setText(listaPlatillos.get(position).getDesc());
        holder.ing.setText(listaPlatillos.get(position).getIng());
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(listaPlatillos.get(position).getImg(),0,listaPlatillos.get(position).getImg().length));
    }

    @Override
    public int getItemCount() { return listaPlatillos.size();
    }

    //SELECCIONAR ID
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nom, precio, ing, desc;
        ImageView img;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom);
            precio = itemView.findViewById(R.id.precio);
            ing = itemView.findViewById(R.id.ing);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.img);
        }
    }

}
