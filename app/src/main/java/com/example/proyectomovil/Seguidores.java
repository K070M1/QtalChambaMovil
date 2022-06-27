package com.example.proyectomovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.proyectomovil.adaptadores.AdaptadorSeguidores;
import com.example.proyectomovil.entidades.ESeguidores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seguidores extends AppCompatActivity {

    ListView listView;
    AdaptadorSeguidores adaptadorSeguidores;
    Button  botonSeguidos;
    ImageButton botonVolver;
    Context context = this;
    RequestQueue requestQueue;

    public ArrayList<ESeguidores>seguidoresArrayList = new ArrayList<>();

    String URLFollower = "http://192.168.18.229/ProtoChamba/controllers/android.controller.php";

    ESeguidores seguidores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguidores);

        requestQueue = Volley.newRequestQueue(context);

        listView = findViewById(R.id.listado_seguidores);
        adaptadorSeguidores = new AdaptadorSeguidores(context, seguidoresArrayList);
        listView.setAdapter(adaptadorSeguidores);

        loadUI();
        listFollower();

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Perfil.class);
                startActivity(intent);
            }
        });

        botonSeguidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Seguidos.class);
                startActivity(intent);
            }
        });
    }


    private void listFollower(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLFollower,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i< jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                String idfollower = object.getString("idfollower");
                                String nombres = object.getString("nombres");
                                String apellidos = object.getString("apellidos");
                                String fechaseguido = object.getString("fechaseguido");

                                seguidores = new ESeguidores(idfollower, nombres, apellidos, fechaseguido);
                                seguidoresArrayList.add(seguidores);
                                adaptadorSeguidores.notifyDataSetChanged();

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

                parametros.put("op", "getFollowerAndroid");
                parametros.put("idusuario", "2");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void loadUI(){

        botonSeguidos = findViewById(R.id.btSeguidos);
        botonVolver = findViewById(R.id.btVolverPerfilSeguidores);

    }
}