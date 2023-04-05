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

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int intentos;
    boolean acierto;

    private Button bComenzar,bVerde,bRojo,bAzul,bAmarillo,bAdivinar1,bAdivinar2,bAdivinar3,bAdivinar4;
    private ListView ltIntentos, ltAciertos;
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
        intentos=10;
        acierto= false;
        reiniciar();
        while (intentos<=10){
            reiniciar();
            generaraColoresRandom();

            if (acierto=true){
                acabarPartida();
                 View negro = findViewById(R.color.negro);
                 View gris = findViewById(R.color.gris);
            }
        }
    }

    private void acabarPartida() {

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
        bAdivinar1.setBackgroundColor(colores[num]);
        bAdivinar2.setBackgroundColor(colores[num]);
        bAdivinar3.setBackgroundColor(colores[num]);
        bAdivinar4.setBackgroundColor(colores[num]);
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