package com.example.proyectomovil.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectomovil.R;
import com.example.proyectomovil.entidades.EEspecialidades;
import com.example.proyectomovil.entidades.ERedesSociales;

import java.util.List;


public class AdaptadorRedSocial extends ArrayAdapter<ERedesSociales> {

    Context context;
    List<ERedesSociales> arrayredsocial;

    public AdaptadorRedSocial(@NonNull Context context, List<ERedesSociales>arrayredsocial) {
        super(context, R.layout.list_redessociales, arrayredsocial);
        this.context = context;
        this.arrayredsocial = arrayredsocial;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_redessociales, null, true);

        TextView txtnombrered = view.findViewById(R.id.tvnombreRedSocial);

        txtnombrered.setText(arrayredsocial.get(position).getRedsocial());


        return view;
    }
}
