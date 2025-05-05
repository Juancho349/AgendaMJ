package com.example.agendamj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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
    AdministradorSQLiteOpenHelper dbAdmin;

    String tipoDocumento = "";
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
        dbAdmin = new AdministradorSQLiteOpenHelper(this, "agenda", null, 1);


    }

    public void buscarUsuario (View view){

        int codigoBuscar = Integer.parseInt(edtDocumento.getText().toString());
        for (Usuario U : dbAdmin.listadoUsuarios()){
            if (U.getId() == codigoBuscar){
                edtNombre.setText(U.getNombre());
                edtApellido.setText(U.getApellido());
                edtEdad.setText(String.valueOf(U.getEdad()));
                edtEmail.setText(U.getEmail());
                edtTelefono.setText(String.valueOf(U.getTelefono()));
                tipoDocumento = U.getTipoDocumento();

                String nivelEducacion = U.getNivelEducativo();
                if (nivelEducacion.equalsIgnoreCase("Primaria")) rbPrimaria.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Secundaria")) rbSecundaria.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Bachillerato")) rbBachillerato.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Tecnico/Tecnólogo")) rbTecnico.setChecked(true);
                else if (nivelEducacion.equalsIgnoreCase("Profesional")) rbProfesional.setChecked(true);

                String generosMusicales = U.getGeneroMusicalPreferido();
                List<String> generosMusicalesLista = Arrays.asList(generosMusicales.split(", "));
                cbSalsa.setChecked(generosMusicalesLista.contains("Salsa"));
                cbReggaeton.setChecked(generosMusicalesLista.contains("Reggaeton"));
                cbBachata.setChecked(generosMusicalesLista.contains("Bachata"));
                cbVallenato.setChecked(generosMusicalesLista.contains("Vallenato"));
                cbOtro.setChecked(generosMusicalesLista.contains("Otro"));

                String deportesFavoritos = U.getDeporteFavorito();
                List<String> deportesFavoritosLista = Arrays.asList(deportesFavoritos.split(", "));
                cbFutbol.setChecked(deportesFavoritosLista.contains("Futbol"));
                cbBasquetball.setChecked(deportesFavoritosLista.contains("Basquetball"));
                cbTenis.setChecked(deportesFavoritosLista.contains("Tenis"));
                cbTenisMesa.setChecked(deportesFavoritosLista.contains("Tenis de mesa"));
                cbVolleyball.setChecked(deportesFavoritosLista.contains("Volleybal"));
            }
        }
    }
    private String obtenerGeneroMusical(){
        StringBuilder generosSeleccionados = new StringBuilder();

        if (cbSalsa.isChecked()) {
            generosSeleccionados.append("Salsa, ");
        }
        if (cbReggaeton.isChecked()) {
            generosSeleccionados.append("Reggaeton, ");
        }
        if (cbBachata.isChecked()) {
            generosSeleccionados.append("Bachata, ");
        }
        if (cbVallenato.isChecked()) {
            generosSeleccionados.append("Vallenato, ");
        }
        if (cbOtro.isChecked()) {
            generosSeleccionados.append("Otro, ");
        }
        //eliminamos la coma con este recorrido
        if (generosSeleccionados.length() > 0) {
            generosSeleccionados.setLength(generosSeleccionados.length() - 2);
        }

        return generosSeleccionados.toString();

    }

    private String  obtenerDeporteFavorito(){
        StringBuilder deporteFavorito = new StringBuilder();
        if (cbFutbol.isChecked()){
            deporteFavorito.append("Fu tbol, ");
        }
        if (cbBasquetball.isChecked()){
            deporteFavorito.append("Basquetball, ");
        }
        if (cbTenis.isChecked()){
            deporteFavorito.append("Tenis, ");
        }
        if (cbTenisMesa.isChecked()){
            deporteFavorito.append("Tenis de mesa, ");
        }
        if (cbVolleyball.isChecked()){
            deporteFavorito.append("Volleybal, ");
        }
        if (deporteFavorito.length() > 0) {
            deporteFavorito.setLength(deporteFavorito.length() - 2);
        }
        return deporteFavorito.toString();
    }

    private String obtenerNivelEducativo(){
        String nivelEducativo = "";
        if (rbPrimaria.isChecked()){
            nivelEducativo += "Primaria";
        }
        if (rbSecundaria.isChecked()){
            nivelEducativo += "Secundaria";
        }
        if (rbBachillerato.isChecked()){
            nivelEducativo += "Bachillerato";
        }
        if (rbTecnico.isChecked()){
            nivelEducativo += "Tecnico/Tecnólogo";
        }
        if (rbProfesional.isChecked()){
            nivelEducativo += "Profesional";
        }
        return nivelEducativo;
    }
    public void actualizar(View view){
        String nombre = edtNombre.getText().toString();
        String apellido = edtApellido.getText().toString();
        int edad = Integer.parseInt(edtEdad.getText().toString());
        String email = edtEmail.getText().toString();
        int telefono = Integer.parseInt(edtTelefono.getText().toString());
        int id = Integer.parseInt(edtDocumento.getText().toString());
        String nivelEducativo = obtenerNivelEducativo();
        String generoMusical = obtenerGeneroMusical();
        String deporteFavorito = obtenerDeporteFavorito();


        Intent intento1 = new Intent();
        intento1.putExtra("nombre", nombre);
        intento1.putExtra("apellido", apellido);
        intento1.putExtra("edad", edad);
        intento1.putExtra("email", email);
        intento1.putExtra("telefono",telefono);
        intento1.putExtra("documento", id);
        intento1.putExtra("tipoDocumento", tipoDocumento);
        intento1.putExtra("nivelEducativo", nivelEducativo);
        intento1.putExtra("generoMusical", generoMusical);
        intento1.putExtra("deporteFavorito", deporteFavorito);
        setResult(Activity.RESULT_OK, intento1);
        Toast.makeText(this, "Usuario actualizado con ID: " + id, Toast.LENGTH_SHORT).show();
        finish();
    }
}