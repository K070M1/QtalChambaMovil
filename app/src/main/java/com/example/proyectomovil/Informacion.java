package com.example.proyectomovil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectomovil.adaptadores.AdaptadorRedSocial;
import com.example.proyectomovil.adaptadores.AdapterEspecialidad;
import com.example.proyectomovil.entidades.EEspecialidades;
import com.example.proyectomovil.entidades.ERedesSociales;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Informacion extends AppCompatActivity {

    ListView listView, listViewRedsocial ;
    AdapterEspecialidad adapterEspecialidad;
    AdaptadorRedSocial adapterRedsocial;
    ImageButton botonVolver, botonEditar, botonAgregarESP;
    Button botonAplicar, botonCancelar;
    EditText txtNombres, txtApellidos, txtTelefono, txtFechaNaci, txtDireccion;
    Context context = this;

    RequestQueue requestQueue;

    public static ArrayList<EEspecialidades>especialidadesArrayList = new ArrayList<>();
    public static ArrayList<ERedesSociales>redesSocialesArrayListt = new ArrayList<>();

    private static final String URL = "http://192.168.18.229/ProtoChamba/controllers/android.controller.php";

    EEspecialidades especialidades;
    ERedesSociales redesSociales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        requestQueue = Volley.newRequestQueue(context);

        //Especialidad
        listView = findViewById(R.id.listado_especialidades);
        adapterEspecialidad = new AdapterEspecialidad(context, especialidadesArrayList);
        listView.setAdapter(adapterEspecialidad);

        //Redes Sociales
        listViewRedsocial =findViewById(R.id.listado_RedSocial);
        adapterRedsocial = new AdaptadorRedSocial(context, redesSocialesArrayListt);
        listViewRedsocial.setAdapter(adapterRedsocial);

        loadUI();
        desabilitar();
        ocultarBotones();
        datosPersona();
        datosSpecialidades();
        datosRedesSociales();


        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Perfil.class);
                startActivity(intent);
            }
        });

        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habilitar();
                mostrarBotones();
            }
        });

        botonAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarAplicar();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarProceso();
            }
        });

        botonAgregarESP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AgregarEspecialidad.class);
                startActivity(intent);
            }
        });




    }


    private void datosPersona(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            txtNombres.setText(jsonObject.getString("apellidos"));
                            txtApellidos.setText(jsonObject.getString("nombres"));
                            txtTelefono.setText(jsonObject.getString("telefono"));
                            txtFechaNaci.setText(jsonObject.getString("fechanac"));
                            txtDireccion.setText(jsonObject.getString("nombrecalle"));

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

                parametros.put("op", "dataPersonAndroid");
                parametros.put("idpersona", "1");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }


    private void modificarDatosPersona(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show();
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

                parametros.put("op", "updatePersonAndroid");
                parametros.put("idpersona", "1");
                parametros.put("apellidos", txtApellidos.getText().toString());
                parametros.put("nombres", txtNombres.getText().toString());
                parametros.put("fechanac", txtFechaNaci.getText().toString());
                parametros.put("telefono", txtTelefono.getText().toString());

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }


    private void datosSpecialidades(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                String idespecialidad = object.getString("idespecialidad");
                                String idservicio = object.getString("idservicio");
                                String idusuario = object.getString("idusuario");
                                String descripcion = object.getString("descripcion");
                                String tarifa = object.getString("tarifa");

                                especialidades = new EEspecialidades(idespecialidad, idservicio, idusuario, descripcion, tarifa);
                                especialidadesArrayList.add(especialidades);
                                adapterEspecialidad.notifyDataSetChanged();

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

                parametros.put("op", "listSpecial");
                parametros.put("idusuario", "1");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }

    private void datosRedesSociales(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                String idredsocial = object.getString("idredsocial");
                                String idusuario = object.getString("idusuario");
                                String redsocial = object.getString("redsocial");

                                char red;
                                red = redsocial.charAt(0);
                                String al = String.valueOf(red);

                                if (red == 'I'){
                                    al  = "Instagram";
                                }else if(red == 'F'){
                                    al = "Facebook";
                                }else if(red == 'W'){
                                    al = "WhatsApp";
                                }else if(red == 'T'){
                                    al = "Twiter";
                                }else if(red == 'Y'){
                                    al = "YouTube";
                                }else if (red == 'K'){
                                    al = "toktok";
                                }

                                String vinculo = object.getString("vinculo");

                                redesSociales = new ERedesSociales(idredsocial, idusuario, al, vinculo);
                                redesSocialesArrayListt.add(redesSociales);
                                adapterRedsocial.notifyDataSetChanged();

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

                parametros.put("op", "redesSocialesAndroid");
                parametros.put("idusuario", "6");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }



    private void verificarAplicar() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        dialogo.setTitle("Q´ tal Chamba")
                .setMessage("¿Esta seguro de aplicar los cambios?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modificarDatosPersona();
                        desabilitar();
                        ocultarBotones();
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

    private void cancelarProceso() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        dialogo.setTitle("Q´ tal Chamba")
                .setMessage("¿Esta seguro de cancelar los cambios?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //limpiarCajas();
                        desabilitar();
                        ocultarBotones();
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

    private void limpiarCajas(){
        txtNombres.setText(null);
        txtApellidos.setText(null);
        txtTelefono.setText(null);
        txtFechaNaci.setText(null);
        txtDireccion.setText(null);
        //txtPruebas.setText(null);
    }

    private void desabilitar(){
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtFechaNaci.setEnabled(false);
        txtDireccion.setEnabled(false);
    }

    private void habilitar(){
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtFechaNaci.setEnabled(true);
        txtDireccion.setEnabled(true);
    }

    private void ocultarBotones(){
        botonAplicar.setVisibility(View.GONE);
        botonCancelar.setVisibility(View.GONE);
    }

    private void mostrarBotones(){
        botonAplicar.setVisibility(View.VISIBLE);
        botonCancelar.setVisibility(View.VISIBLE);
    }



    private void loadUI(){
        botonEditar = findViewById(R.id.btEditar);
        botonAplicar = findViewById(R.id.btAplicar);
        botonCancelar = findViewById(R.id.btCancelar);
        botonVolver = findViewById(R.id.btVolver);
        txtNombres = findViewById(R.id.etNombres);
        txtApellidos = findViewById(R.id.etApellidos);
        txtTelefono = findViewById(R.id.etTelefono);
        txtFechaNaci = findViewById(R.id.etFechaNani);
        txtDireccion = findViewById(R.id.etDireccion);


        botonAgregarESP = findViewById(R.id.btAgregarEspecialidad);
    }
}