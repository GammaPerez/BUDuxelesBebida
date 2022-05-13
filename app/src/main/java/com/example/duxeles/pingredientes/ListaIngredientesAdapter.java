package com.example.duxeles.pingredientes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duxeles.R;

import java.util.ArrayList;

public class ListaIngredientesAdapter extends RecyclerView.Adapter<ListaIngredientesAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<ingrediente> listaIngredientes;
    private View.OnClickListener listener;

    public ListaIngredientesAdapter(ArrayList<ingrediente> listaIngredientes){
        this.listaIngredientes = listaIngredientes;
    }

    @NonNull
    @Override //Asigna el diseño de cada elemento de la lista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ing, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override //Asigna los elementos correspondidentes para cada campo
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nom.setText(listaIngredientes.get(position).getNom());
        holder.cant.setText(String.valueOf(listaIngredientes.get(position).getCant()));
        holder.precio.setText(String.valueOf(listaIngredientes.get(position).getPrecio()));

    }

    @Override
    public int getItemCount() {
       return listaIngredientes.size();
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

        TextView nom, precio, cant;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom);
            precio = itemView.findViewById(R.id.precio);
            cant = itemView.findViewById(R.id.cant);
        }
    }
}
