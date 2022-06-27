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
import com.example.proyectomovil.adaptadores.AdapterSeguidos;
import com.example.proyectomovil.entidades.ESeguidos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seguidos extends AppCompatActivity {

    ListView listView;
    AdapterSeguidos adapter;
    ImageButton botonVolverPerfilSiguiendo, botonDejarSeguir;
    Button botonSeguidores, botonSiguiendo;
    Context context = this;

    RequestQueue requestQueue;

    public static ArrayList<ESeguidos>seguidosArrayList = new ArrayList<>();

    String URLFollower = "http://192.168.18.229/PRACTICA/ProtoChamba/controllers/android.controller.php";
    ESeguidos seguidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguidos);

        requestQueue = Volley.newRequestQueue(context);

        listView = findViewById(R.id.listado_seguidos);
        adapter = new AdapterSeguidos(context, seguidosArrayList);
        listView.setAdapter(adapter);

        loadUI();
        listFollowing();

        botonSeguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Seguidores.class);
                finish();
                startActivity(intent);
            }
        });

        botonVolverPerfilSiguiendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Perfil.class);
                startActivity(intent);

            }
        });


    }


    private void listFollowing(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLFollower,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String idfollowing = object.getString("idfollowing");
                                String nombres = object.getString("nombres");
                                String apellidos = object.getString("apellidos");
                                String fechaseguido = object.getString("fechaseguido");
                                String estado = object.getString("estado");

                                seguidos = new ESeguidos(idfollowing, nombres, apellidos, fechaseguido, estado);
                                seguidosArrayList.add(seguidos);
                                adapter.notifyDataSetChanged();
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

                parametros.put("op", "getFollowingAndroid");
                parametros.put("idusuario", "2");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void deleteFollowing(String idfollowing){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLFollower,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, "Eliminado", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(getApplicationContext(), Perfil.class));
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

                parametros.put("op", "deleteFollowerAndroid");
                parametros.put("idfollower", "2");
                parametros.put("idfollowing", idfollowing);

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }


    private void loadUI(){

        botonSeguidores = findViewById(R.id.btSeguidores);
        botonSiguiendo = findViewById(R.id.btSeguiendo);

        botonVolverPerfilSiguiendo = findViewById(R.id.btVolverPerfilSeguiendo);
        botonDejarSeguir = findViewById(R.id.ibDejarSeguir);

    }

}