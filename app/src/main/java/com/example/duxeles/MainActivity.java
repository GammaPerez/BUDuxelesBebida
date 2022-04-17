package com.example.duxeles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duxeles.pbebidas.bebidas;
import com.example.duxeles.pingredientes.ingrediente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //BOTON PROVICIONAL
    public void Bebida (View view){
        Intent i = new Intent(this, bebidas.class);
        startActivity(i);
    }
    public void ingredientes (View view){
        Intent i = new Intent(this, ingrediente.class);
        startActivity(i);
    }
    //-------------------------
}
