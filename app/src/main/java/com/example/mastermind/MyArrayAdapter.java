package com.example.mastermind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Boton> {

    public MyArrayAdapter( Context context, Boton[] botones) {
        super(context,0, botones);
    }
    public MyArrayAdapter( Context context, List<Boton> botones) {
        super(context,0, botones);
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (getItem(position).getColor() == null)?1:0;
    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NotNull ViewGroup parent){
        Boton b = getItem(position);
        View v;
        ViewHolder vh;
        if (convertView == null){
            int boton_elejido = 0;
            if (b.getColor()==null) {
                boton_elejido = R.layout.lista_botones_elegida;
            }
            v = LayoutInflater.from(getContext()).inflate(
                    boton_elejido,
                    parent,
                    false
            );
            Button boton = v.findViewById(R.id.boton);
            vh = new ViewHolder(boton);
            v.getTag(vh);

        }else {
            v = convertView;
            vh = (ViewHolder) v.getTag();
        }
        vh.getBoton().setText(b.getBoton());

        return b;
    }
}
