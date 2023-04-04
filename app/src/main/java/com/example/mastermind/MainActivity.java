package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    int intentos;
    private Button bVerde,bRojo,bAzul,bAmarillo,bT1,bT2,bT3,bT4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onComenzar(View view) {
        intentos=10;
        while (true){
            for (int i=0; i<=intentos;i++ ){

            }
        }
    }
    private void asignarBotones() {
        bAzul = (Button) findViewById(R.id.bAzul);
        bAmarillo = (Button) findViewById(R.id.bAmarillo);
        bRojo = (Button) findViewById(R.id.bRojo);
        bVerde = (Button) findViewById(R.id.bVerde);
        bT1 = (Button) findViewById(R.id.bT1);
        bT2 = (Button) findViewById(R.id.bT2);
        bT3 = (Button) findViewById(R.id.bT3);
        bT4 = (Button) findViewById(R.id.bT4);
    }
    public int generaraColoresRandom() {
        Set<Integer> indexColors = getFourRandomColors();
        final int[] colores = {
                R.backgroundTint
        };
        cambiarColores();
        return coloresRandom;
    }

    private void cambiarColores() {
        asignarBotones();
        Drawable shape = "@drawagle/shape";
        public void onRojo(View view) {
            ShapeDrawable shapeDrawable = (ShapeDrawable) shape;
            gradientDrawable.setColor(C30404);
        }
        public void onVerde(View view) {
            GradientDrawable gradientDrawable = (GradientDrawable) b.getBackground().mutate();
            gradientDrawable.getColor(Color.GREEN);
        }
        public void onAmarillo(View view) {
            GradientDrawable gradientDrawable = (GradientDrawable) b.getBackground().mutate();
            gradientDrawable.getColor(Color.YELLOW);
        }
        public void onAzul(View view) {
            GradientDrawable gradientDrawable = (GradientDrawable) b.getBackground().mutate();
            gradientDrawable.getColor(Color.BLUE);
        }
    }




}