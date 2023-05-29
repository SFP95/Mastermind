package com.example.mastermind;

import static androidx.core.graphics.ColorKt.toColorInt;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int intentos;
    private Button bComenzar,bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    private  List<Boton> botonesSeleccionados  ;
    private Combinacion _partidaActual;
    private Combinacion _Master;
    private final int max_filas=7;
    private int _fila;
    private int _columna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //asignamos los botones de colores
        asignarBotonesColores();
        //asgnamos los botones de Resultado rnd Colores
        asinarBotonesResultado();
        //asgnamos los botones de intentos
        asignarBotonesintentos();
        //asgnamos los botones de turnos
        asignarBotonesTurnos();

    }

    private void asignarBotonesTurnos() {
        String botonID;
        Button b ;

        for (int i=0; i<=7;i++){
            for (int j=0; j<=4; j++) {
                botonID = "bAcL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                b = findViewById(resId);
            }
        }
    }

    private void asignarBotonesintentos() {
        String botonID;
        Button b ;

        for (int i=0; i<=7;i++){
            for (int j=0; j<=4; j++) {
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


    }

    private  void asinarBotonesResultado(){
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
        generarBotonesColoresRandom(); //esto funciona bien, pero se muestra arriba y acaba la partida

        //creamos el generador de turnos has 10
        while (intentos<=10){
            intentos++;
            //metemos los intentos y las comparaciones por lineas

            if (intentos==10){
                acabarPartida(true);
                Toast.makeText(this,"GANASTE!",Toast.LENGTH_SHORT).show();
            }
        }


        }
    private boolean acabarPartida(boolean ganador) {
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
        return ganador;
    }
    private void reiniciar() {
        String botonID;
        Button b ;

        //activar boton comenzar
        //bComenzar.setEnabled(true);

        //renicniar los botones de adivinacion

        for (int i=0; i<=4;i++){
            botonID="bAdivinar"+i;
            int AdId= getResources().getIdentifier(botonID, "id", getPackageName());
            b= findViewById(AdId);
            if (b != null) {
                b.setVisibility(View.INVISIBLE);
            }
        }

        //reiniciar botones intentos

        for (int i = 0; i <=10; i++) {
            for (int j = 0; j <=4; j++) {
                botonID = "bL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                b = findViewById(resId);
                if (b != null) {
                    b.setVisibility(View.INVISIBLE);
                }
            }
        }

        //reiniciar botones aciertos

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 4; j++) {
                botonID = "bAcL" + i + "_" + j;
                int resId = getResources().getIdentifier(botonID, "id", getPackageName());
                b = findViewById(resId);
                if (b != null) {
                    b.setVisibility(View.INVISIBLE);
                }
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


    public void OnAmarillo(View view) {
        pulsado("amarillo");
    }

    public void OnVerde(View view) {
        pulsado("verde");
    }

    public void OnRojo(View view) {
        pulsado("rojo");
    }

    public void OnAzul(View view) {
        pulsado("azul");
    }

    private void pulsado(String color) {
        boolean terminarPartida = false;
        boolean ganador = false;
        revelarColor(color);

        _partidaActual.addColor(color,_columna);
        _columna = (_columna+1)%4;
        if (_columna==0){
            mostrarBlancoNegro();
            if (_fila < max_filas){
                if (_Master.getNegros(_partidaActual)==4){
                    terminarPartida = true;
                    _fila--;
                }else {
                    _partidaActual = new Combinacion();
                    _fila++;
                }
            }else {
                terminarPartida=true;
            }
        }
        if (acabarPartida(true)){
            Toast.makeText(this,"GANASTE!",Toast.LENGTH_SHORT).show();
        }

    }

    private void mostrarBlancoNegro() {
        int pos = 1;

        for (int i=0;i<_Master.getNegros(_partidaActual);i++) {
            @SuppressLint("DiscouragedApi")
            Button bp = findViewById(getResources().getIdentifier("bp" + _fila + "" + (pos++), "id", getPackageName()));
            bp.setBackgroundColor(Color.BLACK);
            bp.setVisibility(View.VISIBLE);
        }
        for (int i=0;i<_Master.getBlancos(_partidaActual);i++) {
            @SuppressLint("DiscouragedApi")
            Button bp = findViewById(getResources().getIdentifier("bp" + _fila + "" + (pos++), "id", getPackageName()));
            bp.setBackgroundColor(Color.GRAY);
            bp.setVisibility(View.VISIBLE);
        }
    }

    private void revelarColor(String color) {
        @SuppressLint("DiscouragedApi")
        Button b = findViewById(getResources().getIdentifier("bAdivinar" + _fila + "" + (_columna+1), "id", getPackageName()));
        b.setBackgroundColor(toColorInt(color));
        b.setVisibility(View.VISIBLE);
    }
}
