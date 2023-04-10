package com.example.mastermind;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotonAdapter extends ArrayAdapter<Boton>  {
    private static class ViewHolder{
        Button b1;
        Button b2;
        Button b3;
        Button b4;
        Button botonActual;
        ArrayList<Button> botones;
    }
    public BotonAdapter(Context context, List<Boton> botonesSeleccionados) {
        super(context, 0, botonesSeleccionados);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // View v;
        ViewHolder vh;

        if (convertView == null){
            vh = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_botones_elegida,parent,false);

            vh.b1 = convertView.findViewById(R.id.bElegido1);
            vh.b2 = convertView.findViewById(R.id.bElegido2);
            vh.b3 = convertView.findViewById(R.id.bElegido3);
            vh.b4 = convertView.findViewById(R.id.bElegido4);
            vh.botones = new ArrayList<>(Arrays.asList(vh.b1,vh.b2,vh.b3,vh.b4));
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        // Obtener el color y la posición de los botones pulsados desde el MainActivity
        int color1 = getItem(1).getColor();
        int color2 = getItem(2).getColor();
        int color3 = getItem(3).getColor();
        int color4 = getItem(4).getColor();
        int pos1 = getItem(1).getPosicion();
        int pos2 = getItem(2).getPosicion();
        int pos3 = getItem(3).getPosicion();
        int pos4 = getItem(4).getPosicion();

        // Asignar los colores correspondientes a los botones en la lista
        asignarColor(vh.b1,position,color1,pos1);
        asignarColor(vh.b2,position,color2,pos1);
        asignarColor(vh.b3,position,color3,pos1);
        asignarColor(vh.b4,position,color4,pos1);


        return convertView;
    }

    private void asignarColor (Button boton, int position, int color, int posicion){
        int[] colores = getContext().getResources().getIntArray(R.array.colores);

        if (posicion == posicion){
            boton.setBackgroundColor(colores[color]);
        }else{
            boton.setBackgroundColor(Color.WHITE);
        }
    }

}
