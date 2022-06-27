package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CrearPublicacion extends AppCompatActivity {

    ImageButton botonVolverPublicacion;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);

        loadUI();

        botonVolverPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Trabajos.class);
                startActivity(intent);
            }
        });

    }


    private void loadUI(){
        botonVolverPublicacion = findViewById(R.id.btVolverCrearPublicacion);
    }
}