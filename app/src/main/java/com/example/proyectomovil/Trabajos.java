package com.example.proyectomovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Trabajos extends AppCompatActivity {

    ImageButton botonVolver;
    Button botonCrearPublicacion;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos);

        loadUI();

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Perfil.class);
                startActivity(intent);
            }
        });

        botonCrearPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CrearPublicacion.class);
                startActivity(intent);
            }
        });


    }


    private void loadUI(){
        botonVolver = findViewById(R.id.btVolver);
        botonCrearPublicacion = findViewById(R.id.btCrearPublicacion);

    }
}