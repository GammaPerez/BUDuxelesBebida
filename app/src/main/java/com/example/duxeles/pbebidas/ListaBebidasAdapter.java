package com.example.duxeles.pbebidas;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duxeles.R;

import java.util.ArrayList;

public class ListaBebidasAdapter extends RecyclerView.Adapter<ListaBebidasAdapter.ViewHolder> implements View.OnClickListener {

    ArrayList<bebidas> listaBebidas;
    private View.OnClickListener listener;

    public ListaBebidasAdapter( ArrayList<bebidas> listaBebidas){
        this.listaBebidas = listaBebidas;
    }

    @NonNull
    @Override //Asigna el diseño de cada elemento de la lista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articulo, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override //Asigna los elementos correspondidentes para cada campo
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nom.setText(listaBebidas.get(position).getNom());
        holder.precio.setText(String.valueOf(listaBebidas.get(position).getPrecio()));
        holder.desc.setText(listaBebidas.get(position).getDesc());
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(listaBebidas.get(position).getImg(),0,listaBebidas.get(position).getImg().length));
    }

    @Override
    public int getItemCount() {
       return listaBebidas.size();
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
//-----------------------------------------------
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nom, precio, desc;
        ImageView img;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom);
            precio = itemView.findViewById(R.id.precio);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.img);
            //falta img
        }
    }
}
