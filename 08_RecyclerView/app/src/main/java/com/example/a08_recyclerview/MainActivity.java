package com.example.a08_recyclerview;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.a08_recyclerview.adaptador.AdaptadorUsuario;
import com.example.a08_recyclerview.entidad.Usuario;
import com.example.a08_recyclerview.singleton.ListaUsuarioSingleton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView reyclerViewUser;
    private AdaptadorUsuario adaptadorUsuario;
    private Button botonSegunda;

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

        reyclerViewUser = findViewById(R.id.rViewUsuario);
        botonSegunda = findViewById((R.id.segunda));

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        reyclerViewUser.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        ListaUsuarioSingleton.getInstance().inicializar();
        List<Usuario> listaUsuario = ListaUsuarioSingleton.getInstance().getListaUsuarios();
        // Asociamos un adapter (ver más adelante cómo definirlo)
        adaptadorUsuario = new AdaptadorUsuario(listaUsuario);
        reyclerViewUser.setAdapter(adaptadorUsuario);

        botonSegunda.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            startActivity(intent);
        });

     }

    @Override
    protected void onResume() {
        super.onResume();
        //Este método notifica que el conjunto de datos de la lista ha cambiado y que hay
        //que refrescarla
        reyclerViewUser.getAdapter().notifyDataSetChanged();
    }
}