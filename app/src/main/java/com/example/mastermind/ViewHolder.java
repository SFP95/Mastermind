package com.example.mastermind;

import android.widget.Button;

public class ViewHolder {
    private Button bonton;
    
    public ViewHolder(Button bonton){
        this.bonton = bonton;
    }

    public ViewHolder() {

    }

    public Button getBonton(){
        return bonton;
    }

    public void setBonton(Button bonton) {
        this.bonton = bonton;
    }

    public void getBoton() {
    }
}
