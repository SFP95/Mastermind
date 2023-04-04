package com.example.mastermind;

public enum BotonColor {
    SIN_CITY_RED        ("#ED213A","#93291E"),
    CITRUS_PEEL         ("#FDC830", "#F37335"),
    BLUE_RASPBERRY      ("#56CCF2", "#2F80ED"),
    NEUROMANCER         ("#F953C6", "#B91D73"),
    CLEAR_SKY           ("#005C97", "#363795"),
    LUSH                ("#A8E063", "#56AB2f"),
    VIOLET_INFLUENZA    ("#5A175E", "#380038"),
    CARAMEL             ("#825421", "#69140E");
    //Colores en hexagesimal 
    
    private String colorBlanco;
    private String colorNegro;

    BotonColor (String colorBlanco, String colorNegro){
        this.colorBlanco = colorBlanco;
        this.colorNegro = colorNegro;
    }
    public static BotonColor getRandom(){ //AQUI HACEMOS EL RANDOM DE COLORES
        return values()[(int) (Math.random() * values().length)];
    }
    public static BotonColor getNext (BotonColor botonColor){
        return (botonColor == null || botonColor.ordinal() == values().length -1) ? values()[0] : values()[botonColor.ordinal() +1];
    }
    public static BotonColor getNext (Boton boton){
        return (boton == null || boton.getColor().ordinal() == values().length - 1) ? values()[0] : values()[boton.getColor().ordinal() +1];
    }
    public String getColorBlanco(){
        return colorBlanco;
    }
    public String getColorNegro() {
        return colorNegro;
    }
}
