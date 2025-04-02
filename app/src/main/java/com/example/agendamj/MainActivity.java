package com.example.agendamj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bt1Agregar, bt2Modificar, bt3Calculadora;
    private static final int REQUEST_CODE_FORMULARIO = 1;
    private static final int REQUEST_CODE_MODIFICAR_USUARIO = 2;

    ArrayList<Usuario> usuarios = new ArrayList<>();
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
        bt1Agregar = findViewById(R.id.bt1);
        bt2Modificar = findViewById(R.id.bt1);
        bt3Calculadora = findViewById(R.id.bt3);

        bt1Agregar.setOnClickListener(v -> {
            Intent intento1 = new Intent(this, formulario.class);
            startActivityForResult(intento1, REQUEST_CODE_FORMULARIO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FORMULARIO){
            if (resultCode == RESULT_OK){
                Bundle datos = data.getExtras();
                int id = datos.getInt("documento");
                String nombre = datos.getString("nombre");
                String apellido = datos.getString("apellido");
                int edad = datos.getInt("edad");
                String email = datos.getString("email");
                int telefono = datos.getInt("telefono");
                String nivelEducativo = datos.getString("nivelEducativo");
                String generoMusical = datos.getString("generoMusical");
                String deporteFavorito = datos.getString("deporteFavorito");

                Usuario usuario = new Usuario(id, nombre, apellido, edad, email, telefono, nivelEducativo, generoMusical, deporteFavorito);

                usuarios.add(usuario);

            }
        }
    }

    public void usarCalculadora(View v){
        Intent intento3 = new Intent(this, Calculadora.class);
        startActivity(intento3);
    }

}