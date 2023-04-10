package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
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
    private ListView lvIntentos, lvAciertos;
    List<Boton> listaRandomColors = new ArrayList<>();
    private  List<Boton> listaIntentos = new ArrayList<>();
    private  List<Boton> botonesSeleccionados  ;
    private BotonAdapter botonesElegidosAdapter;
    private  BotonAdapter2 botonesAciertosAdapter;
    private Button[] botonesColores ={bRojo,bAmarillo,bAzul,bVerde};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos los botones de colores
        asignarBotonesColores();

        //asignamos las listView
        asignartListViews();

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
    private void asignartListViews() {
        lvAciertos = findViewById(R.id.lv2);
        lvIntentos = findViewById(R.id.lv1);
    }
    @SuppressLint("ResourceType")
    public void onComenzar(View view) {
        intentos=0;
        botonesSeleccionados = new ArrayList<>();
        listaIntentos = (List<Boton>) lvIntentos.getAdapter();
        generarBotonesColoresRandom();
        reiniciar();


        while (intentos<10 && !listaRandomColors.equals(listaIntentos)){
            // limpiar la selección de botones de colores
            botonesSeleccionados.clear();

            Toast.makeText(this,"Has perdido, Intentalo de nuevo",Toast.LENGTH_SHORT).show();

            //espera a que se presione los botones de colores

            while (botonesSeleccionados.size() < listaRandomColors.size()) {
                vecesPulsadasYComparaListas();
                if (botonesSeleccionados.size() == 4) { // Si se han pulsado los 4 botones, comparar listas
                    intentos++;
                    if (listaRandomColors.equals(listaIntentos)) { // Si las listas son iguales, el usuario ha ganado
                        Toast.makeText(this,"¡Felicidades, has ganado!",Toast.LENGTH_SHORT).show();
                        break;
                    } else if (intentos == 10) { // Si se han agotado los intentos, el usuario ha perdido
                        Toast.makeText(this,"Has perdido, intentalo de nuevo",Toast.LENGTH_SHORT).show();
                        break;
                    } else { // Si aún hay intentos disponibles, continuar jugando
                        Toast.makeText(this,"Intento "+ intentos,Toast.LENGTH_SHORT).show();
                        reiniciar();
                    }
                }
            }
        }
    }
    private void vecesPulsadasYComparaListas() {
        List<Boton> listaBotonesSeleccionados = new ArrayList<>(botonesSeleccionados);

        if (listaBotonesSeleccionados.size() == 4) {
            for (int i = 0; i < listaBotonesSeleccionados.size(); i++) {
                Boton botonSeleccionado = listaBotonesSeleccionados.get(i);
                Boton botonAdivinar = listaRandomColors.get(i);

                if (botonSeleccionado.getColor() == botonAdivinar.getColor() && botonSeleccionado.getPosicion() == botonAdivinar.getPosicion()) {
                    botonSeleccionado.setColor(R.color.negro);
                } else if (botonSeleccionado.getColor() == botonAdivinar.getColor() && botonSeleccionado.getPosicion() != botonAdivinar.getPosicion()) {
                    botonSeleccionado.setColor(R.color.gris);
                } else {
                    botonSeleccionado.setColor(R.color.blanco);
                }
            }

            cargarInfoListViews();

            if (listaBotonesSeleccionados.equals(listaRandomColors)) {
                Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
                acabarPartida();
            } else if (intentos == 10 ){
                Toast.makeText(this, "Has perdido, Intentalo de nuevo", Toast.LENGTH_SHORT).show();
                acabarPartida();
            }
        }
    }
    private void cargarInfoListViews() {
        //agregamos los elemento a las listas usando sus adaptadores personalizados

        botonesElegidosAdapter = new BotonAdapter(this, botonesSeleccionados);
        botonesAciertosAdapter = new BotonAdapter2(this, listaRandomColors);

        lvIntentos.setAdapter(botonesElegidosAdapter);
        lvAciertos.setAdapter(botonesAciertosAdapter);
    }
    private void acabarPartida() {
        //mostrar las combinación random en los botonesde arriba

        Button[] botonesAdivinar= { bAdivinar1, bAdivinar2, bAdivinar3, bAdivinar4};

        for (int i=0; i<4;i++){
            int color = listaRandomColors.get(i).getColor();
            int pos = listaRandomColors.get(i).getPosicion();

            botonesAdivinar[pos].setBackgroundColor(getColor(color));
        }

    }
    private void borrarContenidoListView() {
        botonesAciertosAdapter.clear();
        botonesAciertosAdapter.notifyDataSetChanged();
        botonesElegidosAdapter.clear();
        botonesElegidosAdapter.notifyDataSetChanged();
    }
    private void reiniciar() {
        bComenzar.setEnabled(true);
        bAdivinar1.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar2.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar3.setBackgroundColor(getColor(R.color.purple_200));
        bAdivinar4.setBackgroundColor(getColor(R.color.purple_200));

        if (lvAciertos != null && lvIntentos.getAdapter() != null) {
            borrarContenidoListView();
        }
    }
    public void generarBotonesColoresRandom() {
        listaRandomColors.clear();
        Random rnd = new Random();
        int max = botonesColores.length;
        TypedArray colores = getResources().obtainTypedArray(R.array.colores);
        List<Integer> posiciones = Arrays.asList(0,1,2,3);
       // listaRandomColors = new ArrayList<>();

        for (int i=0;i<=4;i++){
            int colorIndex = rnd.nextInt(colores.length());
            int colorResId = colores.getResourceId(colorIndex,0);
            String color = getResources().getResourceEntryName(colorResId);
            int posIndex = rnd.nextInt(posiciones.size());
            int posicion = posiciones.get(posIndex);
            Boton b = new Boton(posicion,color);
            listaRandomColors.add(b);
        }
        Toast.makeText(this, "RandomColors Generados, adivina la combinación",Toast.LENGTH_SHORT).show();
        colores.recycle();
    }
}
