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
    private Color generarColorAzar() {
        Color col = null;
        switch ((int) (Math.random()*4+1)){
            case 1: col=Color.R; break;
            case 2: col=Color.G;break;
            case 3: col=Color.B;break;
            case 4: col=Color.Y;break;
        }
        return col;
    }
    public void generarCombinaciónAzar(){
        for (int i=0; i<4;i++){
            Color color = generarColorAzar();
            _combinacion[i] = color;
            _frecuencia.put(color, _frecuencia.get(color)+1);
        }
    }
    public void addColor (String colorSt, int pos){
        _combinacion[pos]= toColor(colorSt);
        _frecuencia.put(toColor(colorSt), _frecuencia.get(toColor(colorSt))+1);
    }
    private Color toColor(String colotSt){
        Color col = null;
        switch (colotSt){
            case "rojo": col = Color.R;break;
            case "verde": col = Color.G;break;
            case "Azul": col = Color.B;break;
            case "amarillo": col = Color.Y;break;
        }
        return  col;
    }
    public Color[] get_combinacion() {
        return _combinacion;
    }
    public Map<Color, Integer> get_frecuencia() {
        return _frecuencia;
    }
    public void set_combinacion(Color[] _combinacion) {
        this._combinacion = _combinacion;
    }
    public void set_frecuencia(Map<Color, Integer> _frecuencia) {
        this._frecuencia = _frecuencia;
    }
    private int getNumBlacnos(Combinacion blancosCombinacion ){
        int numBlancos = 0;
        for (Color color: Color.values()){
            int miFrecuencia = _frecuencia.get(color);
            int suFrecuencia = blancosCombinacion.get_frecuencia().get(color);

            if (miFrecuencia >= suFrecuencia){
                numBlancos += suFrecuencia;
            }
            if (miFrecuencia < suFrecuencia){
                numBlancos += suFrecuencia;
            }
        }
        return  numBlancos;
    }
}
