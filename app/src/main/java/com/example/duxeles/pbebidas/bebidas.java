package com.example.duxeles.pbebidas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.duxeles.AdminSQLiteOpenHelper;
import com.example.duxeles.R;

import java.util.ArrayList;

import static com.example.duxeles.AdminSQLiteOpenHelper.t_bebidas;

public class bebidas extends AppCompatActivity {

    RecyclerView listaBebidas;
    private int id;
    int SelectId;
    private String nom;
    private Double precio;
    private String desc;
    //private /*byte[]*/int img;
    private byte[] img;

    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

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

    public byte[] getImg() {  return img;  }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas/*activity_main*/);
        listaBebidas= (RecyclerView)findViewById(R.id.recyclerLista);
        LinearLayoutManager linear = new LinearLayoutManager(this);
        listaBebidas.setLayoutManager(linear);
        ListaBebidasAdapter adapter = new ListaBebidasAdapter(mostrarBebida());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectId = mostrarBebida().get(listaBebidas.getChildAdapterPosition(view)).getId();
                    Toast.makeText(bebidas.this, "ID = "+SelectId, Toast.LENGTH_SHORT).show();
            }
        });
        listaBebidas.setAdapter(adapter);

    }
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(bebidas.this);

    public void Modificar (View view){
        int id = SelectId;
        if (id!=0){
            Intent i = new Intent(bebidas.this, mod_bebida.class);
            i.putExtra("id_modificar", id);
            startActivity(i);
        }else{
            Toast.makeText(bebidas.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }

    public void Agregar(View view){
        Intent a = new Intent(bebidas.this, ag_bebida.class);
        startActivity(a);
    }

    public void Eliminar (View view){
        int id = SelectId;
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
                                Base.delete("bebidas","id_bebida=?",Sid);
                                Toast.makeText(getApplicationContext(), "Articulo Eliminado", Toast.LENGTH_LONG).show();
                                Intent a = new Intent(bebidas.this, bebidas.class);
                                startActivity(a);
                                finish();

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .show();
        }else{
            Toast.makeText(bebidas.this, "Seleccione articulo", Toast.LENGTH_SHORT).show();
        }
        id=0;
    }

    public ArrayList<bebidas> mostrarBebida(){
        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(bebidas.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<bebidas> listaBebida = new ArrayList<>();
        bebidas bebida = null;
        Cursor cursorBebida = null;
        cursorBebida = db.rawQuery("SELECT id_bebida, nombreB, precioB, descripcionB, img from bebidas",null);
        if(cursorBebida.moveToFirst()){
            do{
                bebida = new bebidas();
                //cursorBebida.moveToFirst();
                bebida.setId(cursorBebida.getInt(0));
                bebida.setNom(cursorBebida.getString(1));
                bebida.setPrecio(cursorBebida.getDouble(2));
                bebida.setDesc(cursorBebida.getString(3));
                bebida.setImg(cursorBebida.getBlob(4));
                listaBebida.add(bebida); //va llenando una lista con lo que jale de la tabla t_bebidas
            }//do
            while (cursorBebida.moveToNext());
        }//if
        cursorBebida.close();
        return  listaBebida;
    }//mostrarBebida
}
