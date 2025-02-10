package com.example.a13_firebase_01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/*
https://firebase.google.com/docs/guides

Un proyecto de Firebase es un proyecto de Google Cloud con configuraciones y
servicios adicionales específicos de Firebase. Incluso puedes crear un proyecto de Google
Cloud primero y agregar Firebase al proyecto posteriormente.

Todas las aplicaciones por defecto se encuentran en modo desarrollo y bajo el
plan gratis que soporta hasta 100 conexiones concurrentes, 1GB de almacenamiento
y 10GB de transferencia en el backend.

Para crear un proyecto Firebase vamos a usar el flujo de trabajo de
configuración de Firebase console (recomendada).

1. ir a https://console.firebase.google.com/ Se necesita una cuenta de google

2. Creamos un proyecto y le damos la configuración por defecto.

3. Registramos la app al proyecto y seguir los pasos. Para ello pulsamos el botón
de settings y elegimos "configuración del proyecto". Aparecerá una opción de
"Agregar app", elegimos el icono de Android y seguimos los pasos


---

Una vez realizada la configuración de Firebase debemos de crear una base
de datos Firestore con la consola firebase, para ello

1. Seleccionamos Firestore
2. Agregamos una nueva base de datos de pruebas, la podemos situar en europa

---

https://firebase.google.com/docs/reference/android/com/google/firebase/database/package-summary
https://firebase.google.com/docs/firestore/quickstart?hl=es-419#java
https://www.c-sharpcorner.com/article/crud-operations-with-firebase-cloud-fire-store/

 */
public class MainActivity extends AppCompatActivity {
    private Button botonAltaMensaje;
    private EditText etSaludo;
    private RecyclerView rvSaludos;
    AlertDialog progressDialog = null;

    //Este será el nombre de la colección que daremos en la BBDD de Firebase
    public final static String COLECCION = "saludos";

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

        botonAltaMensaje = findViewById(R.id.botonAltaMensaje);
        etSaludo = findViewById(R.id.etSaludo);
        rvSaludos = findViewById(R.id.rvSaludos);
        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        rvSaludos.setHasFixedSize(true);
        // Nuestro RecyclerView usará un linear layout manager
        rvSaludos.setLayoutManager(new LinearLayoutManager(this));

        // Configuración del diálogo de progreso. Thanks Carlos!
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        progressDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        //Llamamos a Firebase para traernos los saludos
        cargarSaludos();

        botonAltaMensaje.setOnClickListener( v -> {
            //Map<String, Object> saludo = new HashMap<>();
            //saludo.put("texto", etSaludo.getText().toString());
            Saludo saludo = new Saludo();
            saludo.setTexto(etSaludo.getText().toString());

            //Siempre que hagamos esperar al usuario, debemos mostrar
            //un dialogo para que piense que estamos procesando su
            //petición
            progressDialog.show();


            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection(COLECCION).add(saludo)
                    .addOnSuccessListener( documentReference -> {
                        Toast.makeText(getApplicationContext(),
                                documentReference.getId(),
                                Toast.LENGTH_SHORT).show();
                        cargarSaludos();
                    }).addOnFailureListener( e -> {
                                Toast.makeText(getApplicationContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                    );
        });

    }

    private void cargarSaludos() {
        progressDialog.show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLECCION).get()
                .addOnSuccessListener(documentSnapshots -> {
                    List<Saludo> listaSaludos = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : documentSnapshots.getDocuments()) {
                        Saludo saludo = documentSnapshot.toObject(Saludo.class);
                        listaSaludos.add(saludo);
                    }
                    AdaptadorSaludo adaptadorSaludo =
                            new AdaptadorSaludo(listaSaludos);
                    rvSaludos.setAdapter(adaptadorSaludo);
                    progressDialog.dismiss();
                }).addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                });
    }
}