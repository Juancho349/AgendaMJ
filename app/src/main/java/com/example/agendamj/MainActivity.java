package com.example.agendamj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bt1Agregar, bt2Modificar, bt3Calculadora, btListar, btPeliculas;
    private static final int REQUEST_CODE_FORMULARIO = 1;
    private static final int REQUEST_CODE_MODIFICAR_USUARIO = 2;
    AdministradorSQLiteOpenHelper dbAdmin;
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
        bt2Modificar = findViewById(R.id.bt2);
        bt3Calculadora = findViewById(R.id.bt3);
        btListar = findViewById(R.id.btListado);
        btPeliculas = findViewById(R.id.bt5);

        bt1Agregar.setOnClickListener(v -> {
            Intent intento1 = new Intent(this, formulario.class);
            startActivityForResult(intento1, REQUEST_CODE_FORMULARIO);
        });

        bt2Modificar.setOnClickListener(v -> {
            Intent intento2 = new Intent(this, Buscar.class);
            intento2.putExtra("ArrayUsuarios", usuarios);
            startActivityForResult(intento2, REQUEST_CODE_MODIFICAR_USUARIO);
        });

        btListar.setOnClickListener(v -> {
            Intent intento4 = new Intent(this, Listado.class);
            startActivity(intento4);
        });

        btPeliculas.setOnClickListener(v -> {
            Intent intento5 = new Intent(this, ModulePeliculas.class);
            startActivityForResult(intento5, REQUEST_CODE_FORMULARIO);
        });
        dbAdmin = new AdministradorSQLiteOpenHelper(this, "agenda", null, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null & data.getExtras() != null){
            Bundle datos = data.getExtras();

            int id = datos.getInt("documento");
            String tipoDocumento = datos.getString("tipoDocumento");
            String nombre = datos.getString("nombre");
            String apellido = datos.getString("apellido");
            int edad = datos.getInt("edad", 0);
            String email = datos.getString("email");
            int telefono = datos.getInt("telefono");
            String nivelEducativo = datos.getString("nivelEducativo");
            String generoMusical = datos.getString("generoMusical");
            String deporteFavorito = datos.getString("deporteFavorito");

            Usuario usuario = new Usuario(id,tipoDocumento, nombre, apellido, edad, email, telefono, nivelEducativo, generoMusical, deporteFavorito);
            if(requestCode == REQUEST_CODE_FORMULARIO){
                usuarios.add(usuario);
                dbAdmin.crear(usuario);//BASE DE DATOS NUEVA LINEA
                respaldoDatos();//Respaldo de cada usuario
            } else if (requestCode == REQUEST_CODE_MODIFICAR_USUARIO) {
                for (Usuario u : usuarios) {
                    if (u.getId() == id) {
                        u.setNombre(nombre);
                        u.setApellido(apellido);
                        u.setEdad(edad);
                        u.setEmail(email);
                        u.setTelefono(telefono);
                        u.setNivelEducativo(nivelEducativo);
                        u.setGeneroMusicalPreferido(generoMusical);
                        u.setDeporteFavorito(deporteFavorito);
                        break;
                    }
                }
                dbAdmin.actualizar(usuario); //ACTUALIZAR;
                respaldoDatos();//Respaldo de cada usuario al actualizar

            }
        }

    }
    public void respaldoDatos(){
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : dbAdmin.listadoUsuarios()){
            texto.append("ID: ").append(usuario.getId()).append("\n");
            texto.append("Tipo de Documento: ").append(usuario.getTipoDocumento()).append("\n");
            texto.append("Nombre: ").append(usuario.getNombre()).append("\n");
            texto.append("Apellido: ").append(usuario.getApellido()).append("\n");
            texto.append("Edad: ").append(usuario.getEdad()).append("\n");
            texto.append("Email: ").append(usuario.getEmail()).append("\n");
            texto.append("Teléfono: ").append(usuario.getTelefono()).append("\n");
            texto.append("Nivel Educativo: ").append(usuario.getNivelEducativo()).append("\n");
            texto.append("Género Musical: ").append(usuario.getGeneroMusicalPreferido()).append("\n");
            texto.append("Deporte Favorito: ").append(usuario.getDeporteFavorito()).append("\n");
            texto.append("--------------\n");
        }
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("Respaldo_datos", Context.MODE_PRIVATE));
            file.write(texto.toString());
            file.flush();
            file.close();
            Toast.makeText(this, "se guardó el archivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "no se guardo", Toast.LENGTH_LONG).show();
        }


    }



    public void usarCalculadora(View v){
        Intent intento3 = new Intent(this, Calculadora.class);
        startActivity(intento3);
    }

}