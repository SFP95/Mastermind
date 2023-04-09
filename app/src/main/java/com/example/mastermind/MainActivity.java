package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int intentos;
    private Button bComenzar,bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    private ListView ltIntentos, ltAciertos;
    List<Boton> listaRandomColors = new ArrayList<>();
    private  List<Boton> listaBotones = new ArrayList<>();
    private  List<Boton> botonesSeleccionados , botonesNOSeleccionados ;
    private BotonAdapter botonesElegidosAdapter;
    private  BotonAdapter2 botonesAciertosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos los botones de colores
        asignarBotonesColores();

        //asignamos las listView
        asignartListViews();

        /*//agregamos info a listas
        cargarInfoListViews();*/

        //agrega los botones a una lista
        // agregarAlistasBotones();

    }
    private void asignarBotonesColores() {
        bComenzar = findViewById(R.id.bComenzar);

        //botones de abajo
        bAzul = (Button) findViewById(R.id.bAzul);
        bAmarillo = (Button) findViewById(R.id.bAmarillo);
        bRojo = (Button) findViewById(R.id.bRojo);
        bVerde = (Button) findViewById(R.id.bVerde);

        //botones de arriba
        bAdivinar1 = (Button) findViewById(R.id.bAdivinar1);
        bAdivinar2 = (Button) findViewById(R.id.bAdivinar2);
        bAdivinar3 = (Button) findViewById(R.id.bAdivinar2);
        bAdivinar4 = (Button) findViewById(R.id.bAdivinar4);
    }
    private void asignartListViews() {
        ltAciertos = findViewById(R.id.lv2);
        ltIntentos = findViewById(R.id.lv1);
    }
    @SuppressLint("ResourceType")
    public void onComenzar(View view) {
        intentos=0;
       // reiniciar();
        // borrarContenidoListView();
        generarBotonesColoresRandom();

        while (intentos<=10){
            // limitacion de pulsaciones boones colores y comparamos las listas y y mostramoss aciertos
            vecesPuladasYComparaListas();
            intentos++;

            if (intentos==10 || listaRandomColors.equals(ltAciertos) ){
                //acabarPartida();
            }
        }
    }
    private void cargarInfoListViews() {
        //iniciamos la lista de botones seleccionados

        botonesSeleccionados = new ArrayList<>();

        //agregamos los elemento a las listas usando sus adaptadores personalizados

        botonesElegidosAdapter = new BotonAdapter( this,botonesSeleccionados);
        botonesAciertosAdapter = new BotonAdapter2(this, botonesSeleccionados);

        ltIntentos.setAdapter(botonesElegidosAdapter);
        ltAciertos.setAdapter(botonesAciertosAdapter);

    }
    private void vecesPuladasYComparaListas() {
        Map<Integer,Integer> contadorCliksBotonesMaps = new HashMap<>(); //almacenamos los contadores de cata boton en esta variable

        Button[] botonesColores ={bRojo,bAmarillo,bAzul,bVerde};

        for (final Button boton : botonesColores){

            //agregamos contador para cada boton del mapa
            contadorCliksBotonesMaps.put(boton.getId(),0);

            //definimos el listener para el boton
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int contadorBoton = contadorCliksBotonesMaps.get(boton.getId());
                    contadorBoton++;
                    contadorCliksBotonesMaps.put(boton.getId(),contadorBoton); //contabilizamos el contador del mapa

                    //generamos una lista de botones selecionados
                    List<Boton> listaBotonesSelect = ltIntentos.findViewById(R.id.lv1);
                    //comparamos posición y color de ñps botones aleatorios
                    for ( int i=0; i<listaBotonesSelect.size(); i++ ){
                        int pos = listaBotonesSelect.get(i).getPosicion();
                        int color = listaBotonesSelect.get(i).getColor();

                        List<Boton> listaBotonesRandom = listaRandomColors;
                        //generamos lista de botones random
                        for (int t=0; t<listaBotonesRandom.size(); t++){
                            int posRnd= listaBotonesRandom.get(t).getPosicion();
                            int colorRnd= listaBotonesRandom.get(t).getColor();

                            //comparamos las listas y  mostramoss tipos de aciertos

                            int menorIgual4= 1;
                            int mayor4=2;
                            switch (contadorCliksBotonesMaps.size()<=4 ? menorIgual4:mayor4){ //por repasar bien
                                case 1:
                                    if (pos == posRnd && color == colorRnd) {
                                        bAdivinar1.setBackgroundColor(getColor(R.color.negro));
                                        bAdivinar2.setBackgroundColor(getColor(R.color.negro));
                                        bAdivinar3.setBackgroundColor(getColor(R.color.negro));
                                        bAdivinar4.setBackgroundColor(getColor(R.color.negro));
                                        acabarPartida();
                                    } else if (pos != posRnd && color != colorRnd) { //caso absoluto de nunguno coincide
                                        bAdivinar1.setBackgroundColor(getColor(R.color.blanco));
                                        bAdivinar2.setBackgroundColor(getColor(R.color.blanco));
                                        bAdivinar3.setBackgroundColor(getColor(R.color.blanco));
                                        bAdivinar4.setBackgroundColor(getColor(R.color.blanco));
                                    } else if (pos != posRnd && color == colorRnd) { // coincide color pero no la posición
                                        bAdivinar1.setBackgroundColor(getColor(R.color.gris));
                                        bAdivinar2.setBackgroundColor(getColor(R.color.gris));
                                        bAdivinar3.setBackgroundColor(getColor(R.color.gris));
                                        bAdivinar4.setBackgroundColor(getColor(R.color.gris));
                                     }
                                    break;
                                case 2:
                                    Toast.makeText(MainActivity.this, "Te has pasado pulsando los botones, solo de hadmiten 4 pulsaciones", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            cargarInfoListViews();
                        }
                    }
                }
            });
        }
    }
    private void acabarPartida() {
        //mostrar las combinación random en los botonesde arriba

        Button[] botonesAdivinar= { bAdivinar1, bAdivinar2, bAdivinar3, bAdivinar4};

        for (int i=0; i<4;i++){
            int color = listaRandomColors.get(i).getColor();
            int pos = listaRandomColors.get(i).getPosicion();

            botonesAdivinar[pos].setBackgroundColor(getColor(color));
        }
    }
    private void borrarContenidoListView() {
        botonesAciertosAdapter.clear();
        botonesAciertosAdapter.notifyDataSetChanged();
        botonesElegidosAdapter.clear();
        botonesElegidosAdapter.notifyDataSetChanged();
    }
    private void reiniciar() {
        bComenzar.setEnabled(true);
        bAdivinar1.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar2.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar3.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar4.setBackgroundColor(getColor(R.color.purple_500));
        borrarContenidoListView();
    }
    public List<Boton> generarBotonesColoresRandom() {
        TypedArray colores = getResources().obtainTypedArray(R.array.colores);
        List<Integer> posiciones = Arrays.asList(0,1,2,3);
        listaRandomColors = new ArrayList<>();
        Random rnd = new Random();

        for (int i=0;i<=4;i++){

            int colorIndex = rnd.nextInt(colores.length());
            int colorResId = colores.getResourceId(colorIndex,0);
            String color = getResources().getResourceEntryName(colorResId);
            int posIndex = rnd.nextInt(posiciones.size());
            int posicion = posiciones.get(posIndex);
            Boton b = new Boton(posicion,color);
            listaRandomColors.add(b);
        }
        colores.recycle();
        return listaRandomColors;
    }
}
