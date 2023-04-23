package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int intentos;
    private Button bComenzar,bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    List<Boton> listaRandomColors = new ArrayList<>();
    private  List<Boton> listaIntentos = new ArrayList<>();
    private  List<Boton> botonesSeleccionados  ;
    private Button[] botonesColores ={bRojo,bAmarillo,bAzul,bVerde};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos los botones de colores
        asignarBotonesColores();
        //asgnamos los botones de intentos
        asignarBotonesintentos();
        //asgnamos los botones de aciertos
        asignarBotonesAciertos();
    }

    private void asignarBotonesAciertos() {
        String botonID;
        Button b ;

        for (int i=0; i<10;i++){
            for (int j=0; j<4; j++) {
                botonID = "bAcL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                /*b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
            );*/
            }
        }
    }

    private void asignarBotonesintentos() {
        String botonID;
        Button b ;

        for (int i=0; i<10;i++){
            for (int j=0; j<4; j++) {
                botonID = "bL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                /*b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
            );*/
            }
        }
    }

    private void asignarBotonesColores() {
        bComenzar = findViewById(R.id.bComenzar);

        //botones de abajo
        bAzul = findViewById(R.id.bAzul);
        bAmarillo = findViewById(R.id.bAmarillo);
        bRojo = findViewById(R.id.bRojo);
        bVerde = findViewById(R.id.bVerde);

        //botones de arriba
        bAdivinar1 = findViewById(R.id.bAdivinar1);
        bAdivinar2 = findViewById(R.id.bAdivinar2);
        bAdivinar3 = findViewById(R.id.bAdivinar3);
        bAdivinar4 = findViewById(R.id.bAdivinar4);
    }

    @SuppressLint("ResourceType")
    public void onComenzar(View view) {
        reiniciar();
        intentos=0;
        botonesSeleccionados = new ArrayList<>();

        generarBotonesColoresRandom();



            //espera a que se presione los botones de colores

            /*while (botonesSeleccionados.size() <= listaRandomColors.size()) {
                if (botonesSeleccionados.size() == 4) { // Si se han pulsado los 4 botones, comparar listas
                    vecesPulsadasYComparaListas();
                    intentos++;
                }
            }*/
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
    private void reiniciar() {
        bComenzar.setEnabled(true);
        bAdivinar1.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar2.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar3.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar4.setBackgroundColor(getColor(R.color.purple_200));

        //reiniciar botones intentos
    }
    public void generarBotonesColoresRandom() {
        listaRandomColors.clear();
        Random rnd = new Random();
        int max = botonesColores.length;
        TypedArray colores = getResources().obtainTypedArray(R.array.colores);
        List<Integer> posiciones = Arrays.asList(0,1,2,3);
       // listaRandomColors = new ArrayList<>();

        for (int i=0;i<=4;i++){
            int colorIndex = rnd.nextInt(colores.length());
            int colorResId = colores.getResourceId(colorIndex,0);
            String color = getResources().getResourceEntryName(colorResId);
            int posIndex = rnd.nextInt(posiciones.size());
            int posicion = posiciones.get(posIndex);
            Boton b = new Boton(posicion,color);
            listaRandomColors.add(b);
        }
        Toast.makeText(this, "RandomColors Generados, adivina la combinación",Toast.LENGTH_SHORT).show();
        colores.recycle();
    }
}
