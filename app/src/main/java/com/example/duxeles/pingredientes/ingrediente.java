package com.example.duxeles.pingredientes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.duxeles.AdminSQLiteOpenHelper;
import com.example.duxeles.R;
import com.example.duxeles.pbebidas.bebidas;
import com.example.duxeles.pplatillos.ListaPlatillosAdapter;
import com.example.duxeles.pplatillos.mod_platillo;
import com.example.duxeles.pplatillos.platillo;

import java.util.ArrayList;

public class ingrediente extends AppCompatActivity {

    RecyclerView listaIng;
    private int id;
    int SelectId;
    private String nom;
    private Double precio;
    private Integer cant;

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(ingrediente.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediente);

        listaIng = (RecyclerView)findViewById(R.id.recyclerLista);
        LinearLayoutManager linear = new LinearLayoutManager(this);
        listaIng.setLayoutManager(linear);
        ListaIngredientesAdapter adapter = new ListaIngredientesAdapter(mostrarIngrediente());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectId = mostrarIngrediente().get(listaIng.getChildAdapterPosition(view)).getId();
                Toast.makeText(ingrediente.this, "ID = "+SelectId, Toast.LENGTH_SHORT).show();
            }
        });
        listaIng.setAdapter(adapter);
    }

    public void Modificar (View view){
        int id = SelectId;
        if (id!=0){
            Intent i = new Intent(ingrediente.this, mod_ing.class);
            i.putExtra("id_modificar", id);
            startActivity(i);
        }else{
            Toast.makeText(ingrediente.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }
    public void Agregar(View view){
        Intent a = new Intent(ingrediente.this, ag_ing.class);
        startActivity(a);
    }

    public void Eliminar (View view){ int id = SelectId;
        if(id!=0){
            final String [] Sid = {String.valueOf(id)};
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar ingrediente")
                    .setMessage("¿Desea eliminar este articulo?")
                    .setNegativeButton(R.string.cancelarEliminacion, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .setPositiveButton(R.string.aceptarEliminacion, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try{
                                SQLiteDatabase Base = admin.getWritableDatabase();
                                Base.delete("ingrediente","id_ing=?",Sid);
                                Toast.makeText(getApplicationContext(), "Articulo Eliminado", Toast.LENGTH_LONG).show();
                                Intent a = new Intent(ingrediente.this, ingrediente.class);
                                startActivity(a);
                                finish();

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Error al eliminar ingrediente", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .show();
        }else{
            Toast.makeText(ingrediente.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }

    public ArrayList<ingrediente> mostrarIngrediente(){
        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(ingrediente.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<ingrediente> listaIng = new ArrayList<>();
        ingrediente ingrediente = null;
        Cursor cursorIngrediente = null;
        cursorIngrediente = db.rawQuery("SELECT id_ing, nombreI, cantidadI, precioI from ingrediente",null);
        if(cursorIngrediente.moveToFirst()){
            do{
                ingrediente = new ingrediente();
                ingrediente.setId(cursorIngrediente.getInt(0));
                ingrediente.setNom(cursorIngrediente.getString(1));
                ingrediente.setPrecio(cursorIngrediente.getDouble(2));
                ingrediente.setCant(cursorIngrediente.getInt(3));
                listaIng.add(ingrediente); //va llenando una lista con lo que jale de la tabla t_bebidas
            }//do
            while (cursorIngrediente.moveToNext());
        }//if
        cursorIngrediente.close();
        return  listaIng;
    }//mostrarIng
}
