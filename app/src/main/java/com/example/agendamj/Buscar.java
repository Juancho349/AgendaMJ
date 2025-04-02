package com.example.agendamj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buscar extends AppCompatActivity {
    EditText edtNombre, edtApellido, edtEdad, edtEmail, edtTelefono, edtDocumento;
    RadioButton rbPrimaria, rbSecundaria, rbBachillerato, rbTecnico, rbProfesional;
    CheckBox cbSalsa, cbReggaeton, cbBachata, cbVallenato, cbOtro;
    CheckBox cbFutbol, cbBasquetball, cbTenis, cbTenisMesa, cbVolleyball;
    Button btBuscar, btActualizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNombre = findViewById(R.id.edt7);
        edtApellido = findViewById(R.id.edt8);
        edtEdad = findViewById(R.id.edt9);
        edtEmail = findViewById(R.id.edt10);
        edtTelefono = findViewById(R.id.edt11);
        edtDocumento = findViewById(R.id.edt12);

        // RadioButton

        rbPrimaria = findViewById(R.id.rb6);
        rbSecundaria = findViewById(R.id.rb7);
        rbBachillerato = findViewById(R.id.rb8);
        rbTecnico = findViewById(R.id.rb9);
        rbProfesional = findViewById(R.id.rb10);


        // CheckBoxes de géneros musicales
        cbSalsa = findViewById(R.id.c);
        cbReggaeton = findViewById(R.id.c11);
        cbBachata = findViewById(R.id.c12);
        cbVallenato = findViewById(R.id.c13);
        cbOtro = findViewById(R.id.c14);

        // CheckBoxes de deportes
        cbFutbol = findViewById(R.id.c15);
        cbBasquetball = findViewById(R.id.c16);
        cbTenis = findViewById(R.id.c17);
        cbTenisMesa = findViewById(R.id.c18);
        cbVolleyball = findViewById(R.id.c19);

        btActualizar = findViewById(R.id.b6);
        btBuscar = findViewById(R.id.b5);

    }

    public void buscarUsuario (View view){
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>)getIntent().getSerializableExtra("ArrayUsuarios");
        int codigoBuscar = Integer.parseInt(edtDocumento.getText().toString());
        for (Usuario U : usuarios){
            if (U.getId() == codigoBuscar){
                edtNombre.setText(U.getNombre());
                edtApellido.setText(U.getApellido());
                edtEdad.setText(String.valueOf(U.getEdad()));
                edtEmail.setText(U.getEmail());
                edtTelefono.setText(String.valueOf(U.getTelefono()));

                String nivelEducacion = U.getNivelEducativo();
                if (nivelEducacion.equalsIgnoreCase("Primaria")) rbPrimaria.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Secundaria")) rbSecundaria.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Bachillerato")) rbBachillerato.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Tecnico/Tecnólogo")) rbTecnico.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Profesional")) rbProfesional.setChecked(true);

                String generosMusicales = U.getGeneroMusicalPreferido();
                List<String> generosMusicalesLista = Arrays.asList(generosMusicales.split(","));
                cbSalsa.setChecked(generosMusicalesLista.contains("Salsa"));
                cbReggaeton.setChecked(generosMusicales.contains("Reggaeton"));
                cbBachata.setChecked(generosMusicales.contains("Bachata"));
                cbVallenato.setChecked(generosMusicales.contains("Vallenato"));
                cbOtro.setChecked(generosMusicales.contains("Otro"));

                String deportesFavoritos = U.getDeporteFavorito();
                List<String> deportesFavoritosLista = Arrays.asList(deportesFavoritos.split(","));
                cbFutbol.setChecked(deportesFavoritosLista.contains("Futbol"));
                cbBasquetball.setChecked(deportesFavoritosLista.contains("Basquetball"));
                cbTenis.setChecked(deportesFavoritosLista.contains("Tenis"));
                cbTenisMesa.setChecked(deportesFavoritosLista.contains("Tenis de mesa"));
                cbVolleyball.setChecked(deportesFavoritosLista.contains("Volleybal"));
            }
        }
    }
}