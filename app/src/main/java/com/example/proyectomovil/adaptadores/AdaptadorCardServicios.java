package com.example.proyectomovil.adaptadores;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectomovil.R;
import com.example.proyectomovil.entidades.ECardsServicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorCardServicios extends ArrayAdapter<ECardsServicios> {

    Context context;
    List<ECardsServicios> arrayCardsServicios;
    List<ECardsServicios> listaDatos ;


    public AdaptadorCardServicios(@NonNull Context context, List<ECardsServicios>arrayCardsServicios) {

        super(context, R.layout.list_servicios, arrayCardsServicios);
        this.context = context;
        this.arrayCardsServicios = arrayCardsServicios;
        this.listaDatos = new ArrayList<>();
    }

    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        listaDatos.addAll(arrayCardsServicios);

        if (longitud == 0){
            arrayCardsServicios.clear();
            arrayCardsServicios.addAll(listaDatos);

        }else{
                List<ECardsServicios> collection = arrayCardsServicios.stream()
                        .filter(i -> i.getEspecialidad().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                arrayCardsServicios.clear();
                arrayCardsServicios.addAll(collection);

        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_servicios, null, true);

        TextView txtNombresCard = view.findViewById(R.id.tvNombresUsuarioCard);
        TextView txtEspecialidad = view.findViewById(R.id.tvEspecialidadCard);
        TextView txtTarifaCard = view.findViewById(R.id.tvTarifaCard);

        txtNombresCard.setText(arrayCardsServicios.get(position).getNombres());
        txtEspecialidad.setText(arrayCardsServicios.get(position).getEspecialidad());
        txtTarifaCard.setText("$/ " + arrayCardsServicios.get(position).getTarifa());

        return view;
    }
}
