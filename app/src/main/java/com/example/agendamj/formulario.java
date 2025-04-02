package com.example.agendamj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formulario extends AppCompatActivity {

    Spinner sp1;
    String documento [] = {"Cedula", "Tarjeta de identidad", "Cedula de extrajeria", "Pasaporte"};
    Button bt1;
    EditText edtNombre, edtApellido, edtEdad, edtEmail, edtTelefono, edtDocumento;
    Spinner spTipoDocumento;
    RadioButton rbPrimaria, rbSecundaria, rbBachillerato, rbTecnico, rbProfesional;
    CheckBox cbSalsa, cbReggaeton, cbBachata, cbVallenato, cbOtro;
    CheckBox cbFutbol, cbBasquetball, cbTenis, cbTenisMesa, cbVolleyball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp1 = findViewById(R.id.sp1);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, documento);
        sp1.setAdapter(adapter);

        bt1 = findViewById(R.id.b1);

        // Inicializar elementos del formulario
        edtNombre = findViewById(R.id.edt1);
        edtApellido = findViewById(R.id.edt2);
        edtEdad = findViewById(R.id.edt3);
        edtEmail = findViewById(R.id.edt4);
        edtTelefono = findViewById(R.id.edt6);
        edtDocumento = findViewById(R.id.edt5);

        // RadioButton

        rbPrimaria = findViewById(R.id.rb1);
        rbSecundaria = findViewById(R.id.rb2);
        rbBachillerato = findViewById(R.id.rb3);
        rbTecnico = findViewById(R.id.rb4);
        rbProfesional = findViewById(R.id.rb5);


        // CheckBoxes de géneros musicales
        cbSalsa = findViewById(R.id.c1);
        cbReggaeton = findViewById(R.id.c2);
        cbBachata = findViewById(R.id.c3);
        cbVallenato = findViewById(R.id.c4);
        cbOtro = findViewById(R.id.c5);

        // CheckBoxes de deportes
        cbFutbol = findViewById(R.id.c6);
        cbBasquetball = findViewById(R.id.c7);
        cbTenis = findViewById(R.id.c8);
        cbTenisMesa = findViewById(R.id.c9);
        cbVolleyball = findViewById(R.id.c10);


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
            deporteFavorito.append("Fubol, ");
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


    public void enviar(View view){
        String nombre = edtNombre.getText().toString();
        String apellido = edtApellido.getText().toString();
        int edad = Integer.parseInt(edtEdad.getText().toString());
        String email = edtEmail.getText().toString();
        int telefono = Integer.parseInt(edtTelefono.getText().toString());
        int id = Integer.parseInt(edtDocumento.getText().toString());
        String tipoDocumento = sp1.getSelectedItem().toString();
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
        finish();
    }


}