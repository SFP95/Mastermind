package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int intentos;
    int cliks=0;
    int cliks_max = 4;



    private Button bComenzar,bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    private ListView ltIntentos, ltAciertos;
    ArrayList<Integer> listaRandomColors = new ArrayList<>();
    private List<Button> listBotonesIntentos, listBotonesAciertos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarBotonesColores();
        ltAciertos = findViewById(R.id.lv2);
        ltIntentos = findViewById(R.id.lv1);
    }
    @SuppressLint("ResourceType")
    public void onComenzar(View view) {

        intentos=0;
        reiniciar();
        generaraColoresRandom();

        while (intentos<=10){

            // limitacion de pulsaciones boones colores y comparamos las listas y y mostramoss aciertos
            vecesPuladasYComparaListas();

            if (intentos==10 || listaRandomColors.equals(ltAciertos) ){
                acabarPartida();
            }
        }
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
                    contadorCliksBotonesMaps.put(boton.getId(),contadorBoton); //cantilizamos el contor del mapa

                    //comparamos las listas y y mostramoss tipos de aciertos

                    switch (contadorCliksBotonesMaps.size() <= 4){
                        case(ltIntentos.equals(ltAciertos)) : //caso absoluto de todos coinciden
                            bAdivinar1.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar2.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar3.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar4.setBackgroundColor(getColor(R.color.negro));
                            break;
                        case (!ltIntentos.equals(ltAciertos)): //caso absoluto de nunguno coincide
                            bAdivinar1.setBackgroundColor(getColor(R.color.blanco));
                            bAdivinar2.setBackgroundColor(getColor(R.color.blanco));
                            bAdivinar3.setBackgroundColor(getColor(R.color.blanco));
                            bAdivinar4.setBackgroundColor(getColor(R.color.blanco));
                            break;
                        case (): // coincide color pero no la posición

                            //coincide en color y posicion
                            bAdivinar1.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar2.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar3.setBackgroundColor(getColor(R.color.negro));
                            bAdivinar4.setBackgroundColor(getColor(R.color.negro));

                            //cohincide en solo color
                            bAdivinar1.setBackgroundColor(getColor(R.color.gris));
                            bAdivinar2.setBackgroundColor(getColor(R.color.gris));
                            bAdivinar3.setBackgroundColor(getColor(R.color.gris));
                            bAdivinar4.setBackgroundColor(getColor(R.color.gris));
                            break;
                    }
                }
            });
        }
    }

    private void acabarPartida() {
        //mostrar las combianación random en los botonesde arriba

        bAdivinar1.setBackgroundColor(listaRandomColors.get(0));
        bAdivinar2.setBackgroundColor(listaRandomColors.get(1));
        bAdivinar3.setBackgroundColor(listaRandomColors.get(2));
        bAdivinar4.setBackgroundColor(listaRandomColors.get(3));
    }
    private void reiniciar() {
        bComenzar.setEnabled(true);
        bAdivinar1.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar2.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar3.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar4.setBackgroundColor(getColor(R.color.purple_500));

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
    public void generaraColoresRandom() {
        int num= (int) (Math.random()*4+1);
        Set<Integer> indexColors = getFourRandomColors();
        final int[] colores = {
                    R.color.rojo,
                    R.color.azul,
                    R.color.amarillo,
                    R.color.verde
        };

        listaRandomColors.add(colores[num]);
        listaRandomColors.add(colores[num]);
        listaRandomColors.add(colores[num]);
        listaRandomColors.add(colores[num]);

      /* bAdivinar1.setBackgroundColor(colores[num]);
        bAdivinar2.setBackgroundColor(colores[num]);
        bAdivinar3.setBackgroundColor(colores[num]);
        bAdivinar4.setBackgroundColor(colores[num]);*/
    }
    private Set<Integer> getFourRandomColors() {
        Set<Integer> set = Collections.emptySet();
        int color;
        Random rnd = new Random();
        while (set.size()<4){
            color = rnd.nextInt(4);
            set.add(color);
        }
        return  set;
    }
}