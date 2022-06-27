package com.example.proyectomovil.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomovil.Seguidos;
import com.example.proyectomovil.entidades.ESeguidos;
import com.example.proyectomovil.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterSeguidos extends ArrayAdapter<ESeguidos>  {

    Context context;
    List<ESeguidos> arrayseguidos;
    //Seguidos idse;


    public AdapterSeguidos(@NonNull Context context, List<ESeguidos>arrayseguidos) {

        super(context, R.layout.list_seguidos, arrayseguidos);
        this.context = context;
        this.arrayseguidos = arrayseguidos;
        //this.idse = new Seguidos();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_seguidos, null, true);

        TextView txtIdFollowing = view.findViewById(R.id.txtIdFollowing);
        TextView txtNombres = view.findViewById(R.id.txtNombress);
        TextView txtApellidos = view.findViewById(R.id.txtApellidoss);
        ImageButton botonDejar = view.findViewById(R.id.ibDejarSeguir);


        txtIdFollowing.setText(arrayseguidos.get(position).getIdfollowing());
        txtNombres.setText(arrayseguidos.get(position).getNombres());
        txtApellidos.setText(arrayseguidos.get(position).getApellidos());

        botonDejar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String idsegui = arrayseguidos.get(position).getIdfollowing();
                //idse.deleteFollowing(idsegui);

                Toast.makeText(context, arrayseguidos.get(position).getIdfollowing(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }



}
