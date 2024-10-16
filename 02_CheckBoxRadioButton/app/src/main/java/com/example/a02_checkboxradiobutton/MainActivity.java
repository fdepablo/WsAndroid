package com.example.a02_checkboxradiobutton;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        //La clase Log te permite crear mensajes del registro que se muestran en logcat.
        // Por lo general, debes usar los siguientes métodos de registro, que se ordenan de
        // mayor a menor prioridad (o del más detallado al menos detallado).
        //Esto nos servirá para poder buscar mensajes de manerá más sencilla

        // Log.e(String, String) (error)
        // Log.w(String, String) (advertencia)
        // Log.i(String, String) (información)
        // Log.d(String, String) (depuración)
        // Log.v(String, String) (registro detallado)

        //Normalmente como primer parámetro le pasaremos la actividad que escribe el Log
        //Como segundo parámetro pondremos un mensaje
        Log.i("MainActivity","Aplicación arrancada");

        final CheckBox cbMarcame = findViewById(R.id.cbMarcame);
        final CheckBox cbMarcame2 = findViewById(R.id.cbMarcame2);
        final CheckBox cbMarcame3 = findViewById(R.id.cbMarcame3);
        final CheckBox cbMarcame4 = findViewById(R.id.cbMarcame4);

        //Metemos el evento mediante una función lambda
        cbMarcame.setOnClickListener(v -> {
            Log.i("MainActivity","cbMarcame pulsado");
            CheckBox checkBox = (CheckBox) v;
            boolean isChecked = checkBox.isChecked();

            if (isChecked) {
                checkBox.setText("Checkbox marcado!");
            } else {
                checkBox.setText("Checkbox desmarcado!");
            }
        });

        //Segunda manera, si vamos a usar muchas veces el mismo comportamiento,
        //mejor hacer una clase de verdad
        cbMarcame2.setOnClickListener(new MiOnClickListenerCheckbox());
        cbMarcame3.setOnClickListener(new MiOnClickListenerCheckbox());
        cbMarcame4.setOnClickListener(new MiOnClickListenerCheckbox());

        final TextView tvMensaje = findViewById(R.id.tvMensaje);
        RadioGroup rgOpciones = findViewById(R.id.rgGrupo1);

        //Este evento se desencadena cuando hay un cambio en el radiogroup
        //Queremos al elegir una nueva opcion que cambie el texto del mensaje
        //Como parametros de entrada tenemos
        //1. El radiogrupo que desencadena el evento
        //2. El id de radiobutton seleccionado
        rgOpciones.setOnCheckedChangeListener((group, checkedId) -> {

            String opcion = "";
            if(checkedId == R.id.rbOpcion1) {
                //podemos acceder a los recursos para parametrizar los valores
                opcion = getResources().getString(R.string.opcion1);
            }else if(checkedId == R.id.rbOpcion2){
                opcion = "Opcion 2 desde código!";
            }
            tvMensaje.setText("Opcion elegida: " + opcion);
        });
    }
}