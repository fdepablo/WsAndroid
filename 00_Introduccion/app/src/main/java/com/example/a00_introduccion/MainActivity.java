package com.example.a00_introduccion;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button boton1;
    private Button boton2;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En la siguiente línea de código, decimos cual de nuestros XMLs queremos asociar a
        // esta actividad. Normalmente se encuentran en "res/layout".
        //"R" es una clase que contiene todos los recursos que hayamos creado en nuestra
        //aplicacion android, es decir, los que haya en la carpeta "res"
        //En este caso cargará todos los elementos que hay en "activity_main"
        //con una estructura de arbol
        setContentView(R.layout.activity_main);

        //Una vez cargados los objetos podemos acceder a ellos a traves de us id
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        textView1 = findViewById(R.id.textView1);

        //Esto es un evento en android. Se pueden usar clases anónimas para manejar los eventos
        //Este evento se lanzará cuando pulsemos el boton1, el código que se ejecutará será
        //el del método onclick(View v) que está en dentro de la interfaz View.OnClickListener
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Con la clase Log podemos mostrar mensajes por el logcat
                Log.i("00_Introduccion","Entrando al boton1");
                //Con Toast podemos mostrar mensajes
                //Toast.makeText(MainActivity.this,"Ha pulsado el boton", Toast.LENGTH_SHORT).show();
            }
        });

        //Equivalente con funciones lambda, mas moderno y recomendable
        boton2.setOnClickListener(v -> {
            Log.i("00_Introduccion","Entrando al boton2");
            textView1.setText("Texto cambiado!!");
        });

    }
}