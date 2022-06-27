package com.example.proyectomovil.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectomovil.Seguidos;
import com.example.proyectomovil.entidades.ESeguidores;
import com.example.proyectomovil.R;

import java.util.List;

public class AdaptadorSeguidores extends ArrayAdapter<ESeguidores> {

    Context context;
    List<ESeguidores> arrayseguidores;

    public AdaptadorSeguidores(@NonNull Context context, List<ESeguidores> arrayseguidores) {

        super(context, R.layout.list_seguidores, arrayseguidores);
        this.context = context;
        this.arrayseguidores = arrayseguidores;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_seguidores, null, true);

        TextView txtIdFollower = view.findViewById(R.id.tvIdFollower);
        TextView txtNombres = view.findViewById(R.id.tvNombresSe);
        TextView txtApellidos = view.findViewById(R.id.tvApellidosSe);

        txtIdFollower.setText(arrayseguidores.get(position).getIdfollower());
        txtNombres.setText(arrayseguidores.get(position).getNombres());
        txtApellidos.setText(arrayseguidores.get(position).getApellidos());


        return view;
    }

}
