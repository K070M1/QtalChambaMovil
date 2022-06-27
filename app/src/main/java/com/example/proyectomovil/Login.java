package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button botonAcceder;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadUI();


        botonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Servicios_Cards.class);
                startActivity(intent);
            }
        });

    }



    private void loadUI(){
        botonAcceder = findViewById(R.id.btnAcceder);
    }
}