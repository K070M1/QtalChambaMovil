package com.example.proyectomovil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    private static final int CODIGO_PORT = 10 ;
    private static final int CODIGO_PERF = 20 ;

    Button botonInformacion, botonForo, botonAmigos, botonTrabajo;
    ImageButton botonEditarDescripcion, botonVolverServicios, botonAplicarDescripcion, botonCancelarDescripcion, botonSubirPortada, botonSubirPerfil;
    TextView txtSeguidores, txtSeguidos;
    ImageView imgPortada, imgPerfil;
    EditText txtDescripcion;
    Context context = this;
    boolean updtImg = true;

    RequestQueue requestQueue;

    private static final String URLCSE = "http://192.168.18.229/ProtoChamba/controllers/follower.controller.php";
    private static final String URL = "http://192.168.18.229/ProtoChamba/controllers/android.controller.php";
    private static final String URLIMG = "http://192.168.18.229/ProtoChamba/dist/img/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        loadUI();
        desabilitarCaja();
        ocurtarBotones();

        requestQueue = Volley.newRequestQueue(context);
        conteoSeguidores();
        description();
        getPictureUser(false);
        getPictureUser(true);


        botonInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Informacion.class);
                startActivity(intent);
            }
        });

        botonAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Seguidores.class);
                startActivity(intent);
            }
        });

        botonForo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Foro.class);
                startActivity(intent);
            }
        });

        botonTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Trabajos.class);
                startActivity(intent);
            }
        });

        botonEditarDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonEditarDescripcion.setVisibility(view.GONE);
                habilitarCaja();
                mostrarBotones();
            }
        });

        botonAplicarDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarAplicar();
            }
        });

        botonCancelarDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarProceso();
            }
        });

        botonVolverServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Servicios_Cards.class);
                startActivity(intent);
            }
        });

        // Accion de subir imagen
        botonSubirPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updtImg = true;
                abriGaleria();
            }
        });

        botonSubirPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updtImg = false;
                abriGaleria();
            }
        });

    }


    // Abrir galeria del usuario
    private void abriGaleria(){
        if(updtImg){
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent.createChooser(intent, "Seleccione"), CODIGO_PORT);
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent.createChooser(intent, "Seleccione"), CODIGO_PERF);
        }
    }

    // Traer imagenes del usuario en el dispositivo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CODIGO_PORT:
                if(updtImg){
                    try {
                        Uri miPath1 = data.getData();
                        //registerImgUser(miPath1);
                        //ToastPrueba(miPath1.toString());
                        imgPortada.setImageURI(miPath1);

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
            case CODIGO_PERF:
                if(updtImg == false){
                    try {
                        Uri miPath2 = data.getData();
                        imgPerfil.setImageURI(miPath2);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
        }

    }

    // Traer imagenes del usuario
    private void getPictureUser(boolean userPerfil){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLCSE,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            if(userPerfil){
                                if(response.isEmpty()){
                                    cargaFotoUsuario("userdefault.jpg", true);
                                }else{
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String archivo = jsonObject.getString("archivo");
                                    cargaFotoUsuario(archivo, true);
                                }
                            }else{
                                if(response.isEmpty()){
                                    cargaFotoUsuario("portdefault.gif", false);
                                }else{
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String archivo = jsonObject.getString("archivo");
                                    cargaFotoUsuario(archivo, false);
                                }
                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                            ToastPrueba("Error aqui");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //txtDescripcion.setText(error.toString());
                        ToastPrueba(error.toString());
                    }
                }

        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                if(userPerfil){
                    param.put("op", "getAPicturePerfil");
                }else{
                    param.put("op", "getAPicturePort");
                }
                param.put("idusuario", "1");
                return param;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void cargaFotoUsuario(String nombreImagen, boolean isPerfil){
        String ruta = URLIMG + nombreImagen;

        ImageRequest imageRequest = new ImageRequest(
                ruta,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        if(isPerfil){
                            imgPerfil.setImageBitmap(response);
                        }else {
                            imgPortada.setImageBitmap(response);
                        }
                    }
                },
                0,
                0,
                null,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastPrueba(error.toString());
                    }
                }
        );
        requestQueue.add(imageRequest);
    }

    // Identificar foto de usuario
    private void registerImgUser(Uri imageenv){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLCSE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ToastPrueba(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastPrueba(error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > parametros = new HashMap<>();
                parametros.put("op", "encontrarName");
                // Falta agregar para que reciba archivos
                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void conteoSeguidores(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLCSE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            txtSeguidores.setText(response);


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

                parametros.put("op", "conteoSeguidores");
                parametros.put("idusuario", "1");

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
                        updateDescription();
                        desabilitarCaja();
                        ocurtarBotones();
                        botonEditarDescripcion.setVisibility(View.VISIBLE);
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
                        desabilitarCaja();
                        ocurtarBotones();
                        botonEditarDescripcion.setVisibility(View.VISIBLE);
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


    private void description(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            txtDescripcion.setText(jsonObject.getString("descripcion"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("op", "listDescripAndroid");
                parametros.put("idusuario", "1");

                return parametros;
            }
        };

        requestQueue.add(stringRequest);

    }

    private void updateDescription(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Descripcion Actualizada", Toast.LENGTH_LONG).show();
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

                parametros.put("op", "updateDescripAndroid");
                parametros.put("idusuario", "1");
                parametros.put("descripcion", txtDescripcion.getText().toString());

                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }




    private void ocurtarBotones(){
        botonAplicarDescripcion.setVisibility(View.GONE);
        botonCancelarDescripcion.setVisibility(View.GONE);
    }

    private void mostrarBotones(){
        botonAplicarDescripcion.setVisibility(View.VISIBLE);
        botonCancelarDescripcion.setVisibility(View.VISIBLE);
    }

    private void habilitarCaja(){
        txtDescripcion.setEnabled(true);
    }

    private void desabilitarCaja(){
        txtDescripcion.setEnabled(false);
    }



    private void loadUI(){
        //NAVEGACIÓN
        botonInformacion = findViewById(R.id.btInformacion);
        botonForo = findViewById(R.id.btForo);
        botonAmigos = findViewById(R.id.btAmigos);
        botonTrabajo = findViewById(R.id.btTrabajos);

        botonVolverServicios = findViewById(R.id.btVolverServicios);
        //DESCRIPCION
        botonAplicarDescripcion = findViewById(R.id.btAplicarDescripcion);
        botonCancelarDescripcion = findViewById(R.id.btCancelarDescripcion);
        botonEditarDescripcion = findViewById(R.id.btEditarDescripcion);
        txtDescripcion = findViewById(R.id.etDescripcion);

        txtSeguidores = findViewById(R.id.txtSeguidores);
        txtSeguidos = findViewById(R.id.txtSeguidos);

        // Subir fotos
        botonSubirPortada = findViewById(R.id.btGPort);
        imgPortada = findViewById(R.id.imgPortUser);
        botonSubirPerfil = findViewById(R.id.btGPerf);
        imgPerfil = findViewById(R.id.imgPerfUser);
    }

    private void ToastPrueba(String envio){
        Toast.makeText(context, envio, Toast.LENGTH_LONG).show();
    }

}