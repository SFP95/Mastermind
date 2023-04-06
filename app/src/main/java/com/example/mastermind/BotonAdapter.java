package com.example.mastermind;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.List;

public class BotonAdapter extends ArrayAdapter<Boton>  {
    private static class ViewHolder{
        Button b1;
        Button b2;
        Button b3;
        Button b4;
    }

    private  final  int listView1;
    private  final  int listView2;
    private  final  LayoutInflater inflater;
    private  final  List<Boton> botonesSeleccionados;
    public BotonAdapter(Context context, List<Boton> botonesSeleccionados, int listView1, int listView2 , LayoutInflater inflater) {
        super(context, 0, botonesSeleccionados);
        this.listView1 = listView1;
        this.listView2 = listView2;
        this.inflater = inflater;
        this.botonesSeleccionados = botonesSeleccionados;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        Boton b = getItem(position);
        ViewHolder vh;

        if (convertView == null){
            vh = new ViewHolder();
             int listViewId = (position % 2 == 0) ? listView1 : listView2;

             convertView = inflater.inflate(listViewId,parent,false);

            if (listViewId == listView1) {
                vh.p
            }
         //   convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_botones_elegida, parent,false);

            vh.b1 = convertView.findViewById(R.id.bElegido1); //esta mal porque tenemos que hacerlo con la posicion de cada boton y lo mismo con el color
            vh.b2 = convertView.findViewById(R.id.bElegido2);
            vh.b3 = convertView.findViewById(R.id.bElegido3);
            vh.b4 = convertView.findViewById(R.id.bElegido4);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        //añadimos los colores y posiciones a la lista

        vh.b1.setText("b1:"+b.getPosicion() + b.getColor());
        vh.b2.setText("b2:"+b.getPosicion() + b.getColor());
        vh.b3.setText("b3:"+b.getPosicion() + b.getColor());
        vh.b4.setText("b4:"+b.getPosicion() + b.getColor());


        return convertView;
    }

}


