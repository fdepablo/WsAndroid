package com.example.a08_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.a08_recyclerview.adaptador.AdaptadorUsuarioPersonalizado;
import com.example.a08_recyclerview.entidad.Usuario;
import com.example.a08_recyclerview.singleton.ListaUsuarioSingleton;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorUsuarioPersonalizado adaptadorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager, esta vez horizontal
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false));

        List<Usuario> listaUsuarios = ListaUsuarioSingleton.getInstance().getListaUsuarios();
        adaptadorUsuario = new AdaptadorUsuarioPersonalizado(listaUsuarios);
        recyclerViewUser.setAdapter(adaptadorUsuario);

        Button btVolver = findViewById(R.id.btVolver);
        btVolver.setOnClickListener(view -> {
            finish();
        });
    }
}