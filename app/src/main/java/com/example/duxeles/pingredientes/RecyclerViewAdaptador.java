package com.example.duxeles.pingredientes;


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


public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ingredienteViewHolder> {

    public static class ingredienteViewHolder extends RecyclerView.ViewHolder{

        private TextView nom, precio, cant;
        public ingredienteViewHolder(View itemView) {
            super(itemView);

            nom=(TextView)itemView.findViewById(R.id.nom);
            precio=(TextView)itemView.findViewById(R.id.precio);
            cant=(TextView)itemView.findViewById(R.id.cant);
        }
    }

    //Variable para almacenar los datos
    public List<ingrediente> ingredienteLista;

    public RecyclerViewAdaptador(List<ingrediente> ingredienteLista) {
        this.ingredienteLista = ingredienteLista;
    }

    //Agrega un nuevo item a la lista haciendo uso de un layout dentro de otro layout
    @NonNull
    @Override
    public ingredienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ing,null,false);
        return new ingredienteViewHolder(view);
    }


    //Realiza las modificaciones del contenido para cada item
    @Override
    public void onBindViewHolder(ingredienteViewHolder holder, int position) {
        holder.nom.setText(ingredienteLista.get(position).getNom());
        holder.precio.setText(String.valueOf(ingredienteLista.get(position).getPrecio()));
        holder.cant.setText(String.valueOf(ingredienteLista.get(position).getCant()));
        }

    //Permite determinar al adaptador la cantidad de elementos que se van a procesar
    @Override
    public int getItemCount() {
        return ingredienteLista.size();
    }
}
