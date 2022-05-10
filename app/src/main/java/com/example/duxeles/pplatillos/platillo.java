package com.example.duxeles.pplatillos;

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
import com.example.duxeles.pbebidas.ListaBebidasAdapter;
import com.example.duxeles.pbebidas.bebidas;
import com.example.duxeles.pbebidas.mod_bebida;
import com.example.duxeles.pingredientes.ag_ing;
import com.example.duxeles.pingredientes.ingrediente;
import com.example.duxeles.pingredientes.mod_ing;

import java.util.ArrayList;

public class platillo extends AppCompatActivity {

    RecyclerView listaPlatillos;
    private int id;
    int SelectId;
    private String nom;
    private Double precio;
    private String desc;
    private String ing;
    private byte[] img;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(platillo.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platillo);
        listaPlatillos= (RecyclerView)findViewById(R.id.recyclerLista);
        LinearLayoutManager linear = new LinearLayoutManager(this);
        listaPlatillos.setLayoutManager(linear);
        ListaPlatillosAdapter adapter = new ListaPlatillosAdapter(mostrarPlatillo());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectId = mostrarPlatillo().get(listaPlatillos.getChildAdapterPosition(view)).getId();

                Toast.makeText(platillo.this, "ID = "+SelectId, Toast.LENGTH_SHORT).show();
            }
        });
        listaPlatillos.setAdapter(adapter);
    }

    public void Modificar (View view){
        int id = SelectId;
        if (id!=0){
            Intent i = new Intent(platillo.this, mod_platillo.class);
            i.putExtra("id_modificar", id);
            startActivity(i);
        }else{
            Toast.makeText(platillo.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }

    public void Agregar(View view){
        Intent a = new Intent(platillo.this, ag_platillo.class);
        startActivity(a);
    }

    public void Eliminar (View view){int id = SelectId;
        if(id!=0){
            final String [] Sid = {String.valueOf(id)};
            new AlertDialog.Builder(this)
                    .setTitle("Eliminacion")
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
                                Base.delete("platillo","id_plat=?",Sid);
                                Toast.makeText(getApplicationContext(), "Articulo Eliminado", Toast.LENGTH_LONG).show();
                                Intent a = new Intent(platillo.this, platillo.class);
                                startActivity(a);
                                finish();

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .show();
        }else{
            Toast.makeText(platillo.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }

    public ArrayList<platillo> mostrarPlatillo(){
        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(platillo.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<platillo> listaPlatillo = new ArrayList<>();
        platillo platillo = null;
        Cursor cursorPlatillo= null;
        cursorPlatillo = db.rawQuery("SELECT id_plat, nombreP, precioP, descripcionP, imgP, ingreP from platillo",null);
        if(cursorPlatillo.moveToFirst()){
            do{
                platillo = new platillo();
                platillo.setId(cursorPlatillo.getInt(0));
                platillo.setNom(cursorPlatillo.getString(1));
                platillo.setPrecio(cursorPlatillo.getDouble(2));
                platillo.setDesc(cursorPlatillo.getString(3));
                platillo.setImg(cursorPlatillo.getBlob(4));
                platillo.setIng(cursorPlatillo.getString(5));
                listaPlatillo.add(platillo); //va llenando una lista con lo que jale de la tabla t_bebidas
            }//do
            while (cursorPlatillo.moveToNext());
        }//if
        cursorPlatillo.close();
        return  listaPlatillo;
    }//mostrarPlatillo

}


