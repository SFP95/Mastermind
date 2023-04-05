package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int intentos;
    private Button bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    private ListView ltIntentos, ltAciertos;
    private List<Button> listBotones;
    private static final Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarBotonesColores();
        ltAciertos = findViewById(R.id.lv2);
        ltIntentos = findViewById(R.id.lv1);
    }
    public void onComenzar(View view) {

        intentos=10;
        reiniciar();
        while (true){
            for (int i=0; i<=intentos;i++ ){
                if (i==10){
                    reiniciar();
                    generaraColoresRandom();
                }
            }
        }
    }

    private void reiniciar() {
        bAdivinar1.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar2.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar3.setBackgroundColor(getColor(R.color.purple_500));
        bAdivinar4.setBackgroundColor(getColor(R.color.purple_500));

    }

    private void asignarBotonesColores() {
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
        Set<Integer> indexColors = getFourRandomColors();
        final int[] colores = {
                    R.color.rojo,
                    R.color.azul,
                    R.color.amarillo,
                    R.color.verde
        };


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
    /*
    private void cambiarColores() {
        public void onRojo(View view) {

            bT1.setBackgroundColor(getColor(R.color.rojo));
            bT2.setBackgroundColor(getColor(R.color.rojo));
            bT3.setBackgroundColor(getColor(R.color.rojo));
            bT4.setBackgroundColor(getColor(R.color.rojo));
        }
        public void onVerde(View view) {
            bT1.setBackgroundColor(getColor(R.color.verde));
            bT2.setBackgroundColor(getColor(R.color.verde));
            bT3.setBackgroundColor(getColor(R.color.verde));
            bT4.setBackgroundColor(getColor(R.color.verde));
        }
        public void onAmarillo(View view) {
            bT1.setBackgroundColor(getColor(R.color.amarillo));
            bT2.setBackgroundColor(getColor(R.color.amarillo));
            bT3.setBackgroundColor(getColor(R.color.amarillo));
            bT4.setBackgroundColor(getColor(R.color.amarillo));
        }
        public void onAzul(View view) {
            bT1.setBackgroundColor(getColor(R.color.azul));
            bT2.setBackgroundColor(getColor(R.color.azul));
            bT3.setBackgroundColor(getColor(R.color.azul));
            bT4.setBackgroundColor(getColor(R.color.azul));
        }
    }*/
}