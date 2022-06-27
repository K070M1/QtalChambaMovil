package com.example.proyectomovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregarEspecialidad extends AppCompatActivity {

    EditText  txtDescripcion, txtTarifa;
    Button botonAgregarEspecialidad;
    Spinner speServicios;
    String[] servicios;
    Map<String, String> servicios_id = new HashMap<>();
    Context context = this;
    RequestQueue requestQueue;

    String valorIdServicio;

    String URLES = "http://192.168.18.229/ProtoChamba/controllers/android.controller.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_especialidad);

        requestQueue = Volley.newRequestQueue(context);

        loadUI();
        listaServicios();


        botonAgregarEspecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarAgregar();
            }
        });


    }



    private void verificarAgregar() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        dialogo.setTitle("Q´Tal Chamba")
                .setMessage("¿Esta seguro de agregar la especialidad?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        agregarEspecialidad();
                        Intent intent = new Intent(context, Informacion.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // se cancela y no hace nada
                    }
                });

        dialogo.show();

    }


    private void listaServicios(){

    StringRequest stringRequest = new StringRequest(
            Request.Method.POST,
            URLES,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        int registro = jsonArray.length();
                        servicios = new String[registro];

                        for (int i = 0; i < registro; i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            servicios[i] = jsonObject.getString("nombreservicio");
                            servicios_id.put(servicios[i], jsonObject.getString("idservicio"));

                            speServicios.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,servicios));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
            ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("op", "listServices");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void agregarEspecialidad(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, "AGREGADO", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();


                parametros.put("op", "registerSpeUser");
                parametros.put("idusuario", "1");
                parametros.put("idservicio", valorIdServicio);
                parametros.put("descripcion", txtDescripcion.getText().toString().trim());
                parametros.put("tarifa", txtTarifa.getText().toString().trim());

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void limpiarCajas(){

        txtDescripcion.setText(null);
        txtTarifa.setText(null);
    }


    private void loadUI(){

        txtDescripcion = findViewById(R.id.etDescripcionSpe);
        txtTarifa = findViewById(R.id.etTarifa);

        speServicios = findViewById(R.id.spServicios);

        botonAgregarEspecialidad = findViewById(R.id.btAgregarEsp);

    }

}