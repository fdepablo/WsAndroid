package com.example.a09_menu_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    En este ejemplo vamos a ver como podemos trabajar con un menu de
    opciones en android


    Para crear la carpeta "menu", primero en la carpeta "res" -> botón derecho
    -> new -> Android Resource Directory

    Luego, creamos un archivo de menú y lo colocamos en dicha carpeta

    Para ello, click derecho en la carpeta "menu" -> New -> Android Resource File.

    Al desplegarse la ventana de configuración, seleccionamos "Menu" en el tipo de recursos y
    luego ponemos el nombre "main_menu"

    Podemos crear los dibujos en la carpeta "drawable" -> botón derecho ->
        New -> Vector Asset

    Por último, tenemos que poner un tema que permita ActionBar, para ello podemos ir a
    la carpeta "res/values/themes" y dentro de los ficheros "themes.xml" establecer como
    padre "Theme.Material3.DayNight", es decir, quitar la parte de "NoActionBar"

    Mas información: https://developer.android.com/develop/ui/views/components/menus?hl=es-419#java
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Este metodo se llamara cuando se cree la actividad e inflaremos el menu
    //El parametro menu sera el objeto donde inflaremos el layout, que nos lo
    //crea android

    //En el valor de retorno decimos si queremos mostrar el menu o no
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("MainActivity","Creando menu de opciones");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Con este método detectaremos que opción del menú ha sido pulsada
    //el parametro MenuItem representa el objeto que fue seleccionado, no puede ser null
    //En el valor de retorno decimos si queremos procesar el elemento en este metodo o no
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.modo_nocturno){
            if(item.isChecked()){
                Toast.makeText(this,"Modo nocturno DESACTIVADO!",Toast.LENGTH_SHORT)
                        .show();
            }else{
                Toast.makeText(this,"Modo nocturno ACTIVADO!",Toast.LENGTH_SHORT)
                        .show();
            }
            //Si esta checkeado lo descheckeamos, y a la inversa
            item.setChecked(!item.isChecked());
            return true;
        }else if(id == R.id.op1){
            Toast.makeText(this,"Opcion 1 elegida!",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            String texto = "Ha pulsado " + item.getTitle().toString();
            Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}