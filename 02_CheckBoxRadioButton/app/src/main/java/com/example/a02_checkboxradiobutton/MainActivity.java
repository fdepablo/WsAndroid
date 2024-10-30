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

        // Log.e(String, String) (error)
        // Log.w(String, String) (advertencia)
        // Log.i(String, String) (información)
        // Log.d(String, String) (depuración)
        // Log.v(String, String) (registro detallado)

        //Esto nos servirá para poder buscar mensajes de manerá más sencilla

        /*
        Los niveles de log permiten clasificar los mensajes según su gravedad o importancia.

        ERROR: Indica errores en el flujo de la aplicación que pueden interrumpir el funcionamiento
        normal, como excepciones y fallos de operaciones importantes. Estos errores suelen
        necesitar atención para ser corregidos.

        WARN: Señala situaciones que podrían ser problemáticas pero que no impiden la ejecución de
        la aplicación. Por ejemplo, el uso de una funcionalidad obsoleta o configuraciones
        potencialmente problemáticas.

        INFO: Indica el funcionamiento normal de la aplicación. Estos mensajes informan sobre el
        progreso y los eventos generales, como el inicio de servicios, carga de configuraciones,
        etc.

        DEBUG: Ofrece detalles de depuración y es comúnmente usado para desarrollo y pruebas.
        Los mensajes DEBUG ayudan a entender el flujo del código y la ejecución de métodos.

        VERBOSE: Proporciona el nivel más detallado de información, útil para rastrear el flujo
        detallado de una aplicación. Suele incluir gran cantidad de información específica para
        depuración profunda.

        Orden de los Niveles
        --------------------
        Si defines un logger con un nivel específico, por ejemplo INFO, solo mostrará mensajes de
        ese nivel y niveles más altos (INFO, WARN, ERROR).
         */

        //Normalmente como primer parámetro le pasaremos la actividad que escribe el Log
        //Como segundo parámetro pondremos el mensaje que queremos mostrar según su gravedad
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

        cbMarcame4.setOnClickListener((v)->{
            CheckBox checkBox = (CheckBox) v;
            boolean isChecked = checkBox.isChecked();

            if (isChecked) {
                checkBox.setText("Checkbox marcado!");
            } else {
                checkBox.setText("Checkbox desmarcado!");
            }
        });

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
                opcion = getResources().getString(R.string.opcion1);
                //Podemos obtener los recursos de diferentes maneras
                //getResources().getString(R.string.app_name);
                //this.getResources().getIdentifier("nameOfDrawable", "drawable", this.getPackageName());
                //int o = MainActivity.this.getResources().getIdentifier("app_name","values",MainActivity.this.getPackageName());
                //System.out.println("El codigo devuelto es: "+o);
                //opcion = getResources().getString(o);
            }else if(checkedId == R.id.rbOpcion2){
                opcion = "Opcion 2 desde código!";
            }
            tvMensaje.setText("Opcion elegida: " + opcion);
        });
    }
}