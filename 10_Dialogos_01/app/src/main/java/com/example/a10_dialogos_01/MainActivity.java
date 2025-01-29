package com.example.a10_dialogos_01;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
Los dialogos son ventanas emergentes que salen encima de las actividades cuyo
objetivo es interactuar con el usuario de alguna manera.

Además, también se suelen usar para evitar que el usuario interaccione con la actividad
principal mientras se cargan datos.

En este ejemplo tenemos 3 tipos de dialogos:

1. Mostramos un dialogo con un solo boton, sirven para avisar a nuestro usuario
de algo
2. Mostramos un dialogo con dos botones, sirven para que el usuario confirme una
accion.
3. Mostramos un dialogo para recoger un valor y mostrarlo en actividad.

Mas información: https://developer.android.com/develop/ui/views/components/dialogs?hl=es-419#java
 */

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;

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

        Button bDialogoUnBoton = findViewById(R.id.bDialogoUnBoton);
        Button bDialogoDosBotones = findViewById(R.id.bDialogoDosBotones);
        Button bDialogoRecogerValor = findViewById(R.id.bDialogoRecogerValor);
        Button bDialogoEnProceso = findViewById(R.id.bDialogoEnProceso);
        tvResultado = findViewById(R.id.tvResultado);

        bDialogoUnBoton.setOnClickListener(v -> {
            //Para crear dialogos se usa la clase AlertDialog.Builder
            //importamos de androidx.appcompat.app.AlertDialog para hacer retrocompatible
            //el código
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            //Los dialogos en su manera más sencilla tiene un titulo y un mensaje
            //a mostrar
            builder.setTitle("Gardar Datos");
            builder.setMessage("Se han guardado los datos satisfactoriamente!");
            //Al menos tenemos que incluir un boton de afirmacion
            //Si no le pasamos ningun listener, por defecto cerramos el dialogo
            builder.setPositiveButton("Aceptar", null);

            //Instanciamos el objeto
            AlertDialog dialog = builder.create();
            //Mostramos al usuario el dialogo
            dialog.show();
        });

        bDialogoDosBotones.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Dialogo de dos botones");
            builder.setMessage("¿Desea cerrar la aplicacion?");

            //En este caso si que voy a poner un listener al boton, ya que
            //quiero cerrar la actividad
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            //Es este caso tambien metemos el boton en caso de que no quiera
            //hacer la accion
            builder.setNegativeButton("Cancelar", null);

            AlertDialog dialog = builder.create();
            //Establecemos que no se cancele el dialogo si pulsa fuera del mismo
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });

        bDialogoRecogerValor.setOnClickListener(v -> {
            //Creamos un objeto del tipo Dialogo que hemos creado antes y
            //lo mostramos.
            DialogoRecogerValor dialogoRecogerValor = new DialogoRecogerValor();
            dialogoRecogerValor.setView(tvResultado);
            dialogoRecogerValor.show(getSupportFragmentManager(),"MainActivity");
        });

        // AlertDialog de "Progreso" Thanks Carlos! :)

        // Primero inflamos el "progress_dialog.xml", que es el que vamos
        // a usar para mostrar en el AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        //Creamos el dialogo
        final AlertDialog progressDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                //.setCancelable(false)
                .create();

        bDialogoEnProceso.setOnClickListener(view -> {
            Log.i("MainActivity","Mostrando process");
            //Mostramos el dialogo
            progressDialog.show();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Log.i("MainActivity","Cancelando process");
            //Cancelamos el dialogo
            progressDialog.dismiss();
        });
    }
}