package com.example.a04_intenciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a04_intenciones.modelo.entidad.Usuario;

/*
En este ejemplo vamos a ver como podemos pasar de una actividad a otra y como
podemos mandarle información

Para crear una actividad pulsamos boton derecho sobre el paquete que queremos
y elegimos "Activity". En este caso nos crearia la clase MainActivity, el
layout asociado y el en AndroidManifest.xml se daría visibilidad al contexto de
Android.
 */
public class MainActivity extends AppCompatActivity {
    private Button botonSiguienteActividad;
    private EditText textoNombreUsuario,textoPasswordUsuario;

    //Declaramos las claves de los valores para luego recogerlas en la SecondActivity
    public final static String K_NOMBRE_USUARIO = "nombre";
    public final static String K_PASSWORD_USUARIO = "pass";
    public final static String K_USUARIO = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonSiguienteActividad = findViewById(R.id.botonSiguienteActividad);
        textoNombreUsuario = findViewById(R.id.nombreUsuario);
        textoPasswordUsuario = findViewById(R.id.passwordUsuario);

        botonSiguienteActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //La clase Log te permite crear mensajes del registro que se
                // muestran en logcat. Por lo general, debes usar los siguientes
                // métodos de registro, que se ordenan de mayor a menor prioridad
                // (o del más detallado al menos detallado).

                // Log.e(String, String) (error)
                // Log.w(String, String) (advertencia)
                // Log.i(String, String) (información)
                // Log.d(String, String) (depuración)
                // Log.v(String, String) (registro detallado)

                Log.d("MainActivity","Pasando a la siguiente actividad");

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                //Si la actividad tiene un intent-filter, podemos lanzar la intención de la siguiente
                //manera:
                //Intent intent = new Intent("com.example.a04_intenciones.SecondActivity");
                //Esto puede servir para llamar actividades desde diferentes aplicaciones en las que
                //no tenemos su clase. El intent filter tenemos que añadirlo nosotros

                String nombreUsuario = textoNombreUsuario.getText().toString();
                String passwordUsuario = textoPasswordUsuario.getText().toString();

                intent.putExtra(K_NOMBRE_USUARIO, nombreUsuario);
                intent.putExtra(K_PASSWORD_USUARIO, nombreUsuario);

                Usuario usuario = new Usuario();
                usuario.setNombre(nombreUsuario);
                usuario.setPassword(passwordUsuario);

                intent.putExtra(K_USUARIO,usuario);

                //Decimos a android que vaya a dicha Activity
                startActivity(intent);
            }
        });
    }
}