package com.example.a01_textviewedittext;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//INTRODUCCIÓN


//ACTIVIDADES
//-----------
//Las actividades son clases que sirven de intermediarios entre las vistas y la lógica.
//Hacen la llamada función de controlador

// Los objetos de esta clase serán creados por Android, es decir, no llevamos el control de su
// ciclo de vida. Como programadores nos limitamos a programarlos para darles funcionalidad.
// El que lleva el control de su ciclo de vida será Android. Esta carateristica se llama
// Inversion de Control(IoC). Es decir ,en un entorno nomal nosotros como programadores creamos
// los objetos (con new) y mantenemos el objeto vivo mediante referencias. En un entorno de IoC
// nosotros NO creamos los objetos ni nosotros nos ocupamos de manener el objeto con vida.
// Sobre el ciclo de vida se hablará más adelante

// Mas información:
// https://developer.android.com/guide/components/activities/activity-lifecycle?hl=es

public class MainActivity extends AppCompatActivity {

    //Normalemente declararemos como atributo de la clase activity aquellos componentes
    //con lo que queremos actuar
    private Button botonPulsame;
    private TextView textView;
    private Button botonFactorial;
    private TextView resultadoFactorial;
    private EditText textoFactorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        /*
        EdgeToEdge.enable(this) activa el modo Edge-to-Edge, permitiendo que la app se extienda
        debajo de la barra navegación, dándole un diseño más fluido y moderno que
        utiliza toda la pantalla del dispositivo.
         */
        setContentView(R.layout.activity_main);
        /*
        Este código detecta los márgenes que ocupan las barras de estado y de navegación del
        sistema en la pantalla, y ajusta el relleno de la vista main para que su contenido no
        quede oculto bajo dichas barras. Es útil para garantizar que la interfaz de usuario
        respete los espacios ocupados por estos elementos del sistema en dispositivos
        Android modernos.
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Android mediante la inversion de control nos creo todos los objetos que
        //encontro el el fichero activity_main.xml.
        //Pero si nosotros queremos trabajar con dichos objetos tendremos que
        //de alguna manera acceder al dicho objeto, es decir, queremos que android
        //nos inyecte un objeto que tenga creado.
        //Normalmente le diremos a Android que queremos acceder a un objeto a traves
        //de su "id" usando para ello la funcion findElementById, pasandole el id
        //que queremos que nos inyecte
        //Desde el API 26, findViewById usa inferencia de tipos para obtener
        //el valor de retorno. En versiones anteriores hacia falta castear el objeto
        //devuelto
        //botonPulsame = (Button)findViewById(R.id.botonPulsame);
        botonPulsame = findViewById(R.id.botonPulsame);

        //Vamos a meterle lógica al botón para que cuando le pulsemos nos cambie
        //el texto del textView

        textView = findViewById(R.id.texto1);

        //Metemos un evento onclick al boton para que se ejecute cuando lo
        //pulsemos. Esto es crear un objeto y una clase en el mismo momento
        //y en java se llaman clases anonimas
        botonPulsame.setOnClickListener(new View.OnClickListener() {
            int contador = 0;

            @Override
            public void onClick(View v) {
                textView.setText("Ha pulsado el boton " + ++contador);
            }
        });

        botonFactorial = findViewById(R.id.botonFactorial);
        textoFactorial = findViewById(R.id.textoFactorial);
        resultadoFactorial = findViewById(R.id.resultadoFactorial);

        botonFactorial.setOnClickListener(v -> {
            //Los valores hay que cambiar el tipo, lo que nos entra
            //son Strings
            String sNumero = textoFactorial.getText().toString();
            //Lo convertimos a numero
            int iNumero = Integer.parseInt(sNumero);
            //Calculamos el factorial
            int resultado = 1;
            for(int i=1;i<=iNumero;i++){
                resultado *= i;
            }
            //Establecemos el valor pero antes convertimos el tipo int
            //en tipo String
            resultadoFactorial.setText(String.valueOf(resultado));
        });
    }

    //Podemos hacer que un boton ejecute directamente una funcion sin necesidad
    //de meterle un listener. En este caso debemos de decirle el nombre de la funcion
    //dentro del atributo "onclick" del boton
    public void cambiarTexto(View view){
        textView.setText("Has pulsado otro boton");
    }

}