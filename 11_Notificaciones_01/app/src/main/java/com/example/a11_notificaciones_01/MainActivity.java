package com.example.a11_notificaciones_01;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
El framework de Android nos proporciona las clases NotificationManagerCompat, NotificationCompat
y NotificationChannel para instanciar a las notificaciones y mostrarlas.

En esencia, ensamblamos el código con las siguientes participaciones:

    1. Crear y registrar un canal de notificaciones (NotificationChannel),
    esto es obligatorio desde Android 8.0
    2. Crear la notificación (NotificationCompat.Builder)
    3. Mostrar la notificación (NotificationManagerCompat.notify())

Mas informacion: https://developer.android.com/training/notify-user/build-notification?hl=es-419
*/

public class MainActivity extends AppCompatActivity {

    private EditText etMensaje;

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

        etMensaje = findViewById(R.id.etMensaje);
        createNotificationChannel();

        Button botonNotificacion = findViewById(R.id.botonNotificacion);
        botonNotificacion.setOnClickListener(v -> {
            Log.i("MainActivity","Enviando la notificacion...");
            enviarNotificacion();
        });
    }

    /*
   Desde Android 8.0 (API 26) es necesario incluir el registro de canales de
   notificaciones para configurar su estilo visual y la importancia desde el sistema.

   Los canales son representados por la clase NotificationChannel e instanciamos
   sus objetos con el metodo createNotificationChannel().

   Este metodo crea dicho canal
   */
    private void createNotificationChannel() {
        //Preguntando si la version es superior a la 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1234", "CanalNotificacion", importance);
            channel.setDescription("Canal de prueba");
            // Registramos el canal en el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void enviarNotificacion(){
        //Pedimos el canal creado anteriormente
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1234")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notificacion!!!")
                .setContentText("Mensaje: " + etMensaje.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompact =
                NotificationManagerCompat.from(this);

        // NotificacionId deberia de ser unico por cada notificacion
        int notificationId = 1;

        //Desde la versión 13 de Android añadir un permiso en el AndroidManifest.xml para que la
        //aplicación pueda mandar notificaciones.
        //Además debemos de checkear en el código si los permisos estan garantizados con el siguiente
        //condicional
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i("MainActivity","Faltan permisos para enviar la notificacion");
            Toast.makeText(MainActivity.this,"Faltan Permisos!",Toast.LENGTH_SHORT).show();
            return;
        }
        //Enviamos la notificacion si todos los permisos estan OK
        notificationManagerCompact.notify(notificationId, builder.build());
    }
}