package com.example.proyectomovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectomovil.adaptadores.AdaptadorCardServicios;
import com.example.proyectomovil.entidades.ECardsServicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servicios_Cards extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView listViewCards;
    SearchView searchView;
    ImageButton btnPerfil;
    AdaptadorCardServicios adaptadorCardServicios;
    Context context = this;

    RequestQueue requestQueue;
    public static ArrayList<ECardsServicios>cardsServiciosArrayList = new ArrayList<>();

    String URLSERV = "http://192.168.18.229/ProtoChamba/controllers/android.controller.php";
    ECardsServicios eCardsServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_cards);

        requestQueue = Volley.newRequestQueue(context);

        listViewCards = findViewById(R.id.listado_card_servicios);
        adaptadorCardServicios = new AdaptadorCardServicios(context, cardsServiciosArrayList);
        listViewCards.setAdapter(adaptadorCardServicios);

        loadUI();
        listServicesCards();

        searchView.setOnQueryTextListener(this);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Perfil.class);
                startActivity(intent);
            }
        });
    }

    private void listServicesCards(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLSERV,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String idespecialidad = object.getString("idespecialidad");
                                String idusuario = object.getString("idusuario");
                                String idservicio = object.getString("idservicio");
                                String especialidad = object.getString("especialidad");
                                String nombres = object.getString("nombres");
                                String email = object.getString("email");
                                String nombreservicio= object.getString("nombreservicio");
                                String telefono = object.getString("telefono");
                                String tarifa= object.getString("tarifa");

                                eCardsServicios = new ECardsServicios(idespecialidad, especialidad, nombres, email, nombreservicio , idusuario, idservicio, telefono, tarifa);
                                cardsServiciosArrayList.add(eCardsServicios);
                                adaptadorCardServicios.notifyDataSetChanged();
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
                Map<String, String> parametros  = new HashMap<>();

                parametros.put("op", "listServicesAndroid");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }


    private void loadUI(){
        searchView = findViewById(R.id.buscador);
        btnPerfil = findViewById(R.id.btPerfil);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptadorCardServicios.filtrado(newText);
        return false;
    }
}