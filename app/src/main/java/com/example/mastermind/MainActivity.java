package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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
                b = findViewById(resId);
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
                b = findViewById(resId);
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

        //generamos la combinación de colores random
        generarBotonesColoresRandom();

        //creamos el generador de turnos has 10
        while (intentos<=10){
            intentos++;
            //metemos los intentos y las comparaciones por lineas

            if (intentos==10){
                acabarPartida();
                Toast.makeText(this,"GANASTE!",Toast.LENGTH_SHORT).show();
            }
        }


        }
    private void acabarPartida() {
        //mostrar las combinación random en los botonesde arriba

            bAdivinar1.setActivated(true);
            bAdivinar2.setActivated(true);
            bAdivinar3.setActivated(true);
            bAdivinar4.setActivated(true);

        /*Button[] botonesAdivinar= { bAdivinar1, bAdivinar2, bAdivinar3, bAdivinar4};
        botonesAdivinar.
        for (int i=0; i<4;i++){
            int color = listaRandomColors.get(i).getColor();
            int pos = listaRandomColors.get(i).getPosicion();

            botonesAdivinar[pos].setBackgroundColor(getColor(color));
        }*/
    }
    private void reiniciar() {
        String botonID;
        Button b ;

        //activar boton comenzar
        bComenzar.setEnabled(true);

        //renicniar los botones de adivinacion

        for (int i=0; i<=4;i++){
            botonID="bAdivinar"+i;
            int AdId= getResources().getIdentifier(botonID, "id", getPackageName());
            b= findViewById(AdId);
            b.setActivated(false);
        }

        //reiniciar botones intentos

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <=4; j++) {
                botonID = "bL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                b = findViewById(resId);
                b.setActivated(false);
            }
        }

        //reiniciar botones aciertos

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                botonID = "bAcL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                b = findViewById(resId);
                b.setActivated(false);
            }
        }


    }
    public void generarBotonesColoresRandom() {

        int[] colores = {Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE};
        Random rnd= new Random();

        bAdivinar1.setBackgroundColor(colores[rnd.nextInt(colores.length)]);
        bAdivinar2.setBackgroundColor(colores[rnd.nextInt(colores.length)]);
        bAdivinar3.setBackgroundColor(colores[rnd.nextInt(colores.length)]);
        bAdivinar4.setBackgroundColor(colores[rnd.nextInt(colores.length)]);

        Toast.makeText(this, "RandomColors Generados, adivina la combinación",Toast.LENGTH_SHORT).show();

    }
}
