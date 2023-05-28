package com.example.mastermind;

public class Boton {
    boolean isSelected;
    private int color;
    private int posicion;

    public Boton (boolean isSelected ,int color,int posicion){
        this.isSelected = isSelected;
        this.color = color;
        this.posicion = posicion;
    }

    public Boton(int posicion, String color) {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}