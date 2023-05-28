package com.example.mastermind;

import java.util.HashMap;
import java.util.Map;

public class Combinacion {
    public enum Color {R,G,B,Y};
    private Color[] _combinacion;
    private Map<Color, Integer> _frecuencia;

    public Combinacion(){
        iniciarAtributos();
    }

    private void iniciarAtributos() {
        _combinacion = new Color[4];
        _frecuencia = new HashMap<>();
        for (Color color : Color.values()){
            _frecuencia.put(color,0);
        }
    }
}
