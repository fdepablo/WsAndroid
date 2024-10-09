package com.example.a03_intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a03_intent.modelo.entidad.Usuario;

/*
En este ejemplo vamos a ver como podemos pasar de una actividad a otra y como
podemos mandarle información

Para crear una actividad pulsamos boton derecho sobre el paquete que queremos
y elegimos "Activity". En este caso nos crearia la clase SecondActivity, el
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botonSiguienteActividad = findViewById(R.id.botonSiguienteActividad);
        textoNombreUsuario = findViewById(R.id.nombreUsuario);
        textoPasswordUsuario = findViewById(R.id.passwordUsuario);

        botonSiguienteActividad.setOnClickListener(v -> {
            Log.d("MainActivity","Pasando a la siguiente actividad");

            //Intent será el objeto que nos permite navegar entre actividades.
            //Para crear un Intent debemos de pasarle el contexto y la actividad
            //a la que queremos ir.
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            //Si la actividad tiene un intent-filter en el AndroidManifest.xml, podemos lanzar
            // la intención de la siguiente manera:
            //Intent intent = new Intent("com.example.a04_intenciones.SecondActivity");
            //Esto puede servir para llamar actividades desde diferentes aplicaciones en las que
            //no tenemos su clase.

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
        });
    }
}