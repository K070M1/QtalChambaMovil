package com.example.proyectomovil.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectomovil.AgregarEspecialidad;
import com.example.proyectomovil.entidades.EEspecialidades;
import com.example.proyectomovil.R;

import java.util.List;

public class AdapterEspecialidad extends ArrayAdapter<EEspecialidades> {

    Context context;
    List<EEspecialidades> arrayespecialidades;


    public AdapterEspecialidad(@NonNull Context context, List<EEspecialidades>arrayespecialidades) {

        super(context, R.layout.list_especialidades, arrayespecialidades);
        this.context = context;
        this.arrayespecialidades = arrayespecialidades;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_especialidades, null, true);

        TextView txtDescripcion = view.findViewById(R.id.txtEspecilidad);
        TextView txtTarifa = view.findViewById(R.id.txtTarifa);
        ImageButton btEditar = view.findViewById(R.id.btEditarEspecialidades);


        txtDescripcion.setText(arrayespecialidades.get(position).getDescripcion());
        txtTarifa.setText(arrayespecialidades.get(position).getTarifa());



        return view;
    }

}
