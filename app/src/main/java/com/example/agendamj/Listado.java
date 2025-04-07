package com.example.agendamj;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {
    TableLayout tablaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tablaUsuarios = findViewById(R.id.tableLayout2);
        llenarTabla();
    }

    public void llenarTabla (){
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) getIntent().getSerializableExtra("ListadoUsuarios");

        for (Usuario u: usuarios){
            TableRow fila = new TableRow(this);

            TextView tvpCedula = new TextView(this);
            tvpCedula.setText(String.valueOf(u.getId()));
            tvpCedula.setPadding(10, 10, 10, 10);


            TextView tvpNombre = new TextView(this);
            tvpNombre.setText(u.getNombre());
            tvpNombre.setPadding(10, 10, 10, 10);

            TextView tvpApellido = new TextView(this);
            tvpApellido.setText(u.getApellido());
            tvpApellido.setPadding(10, 10, 10, 10);

            TextView tvpEdad = new TextView(this);
            tvpEdad.setText(String.valueOf(u.getEdad()));
            tvpEdad.setPadding(10, 10, 10, 10);

            TextView tvpEmail = new TextView(this);
            tvpEmail.setText(u.getEmail());
            tvpEmail.setPadding(10, 10, 10, 10);

            TextView tvpTelefono = new TextView(this);
            tvpTelefono.setText(String.valueOf(u.getTelefono()));
            tvpTelefono.setPadding(10, 10, 10, 10);

            TextView tvpDeporte = new TextView(this);
            tvpDeporte.setText(u.getDeporteFavorito());
            tvpDeporte.setPadding(10, 10, 10, 10);

            fila.addView(tvpCedula);
            fila.addView(tvpNombre);
            fila.addView(tvpApellido);
            fila.addView(tvpEdad);
            fila.addView(tvpEmail);
            fila.addView(tvpTelefono);
            fila.addView(tvpDeporte);

            tablaUsuarios.addView(fila);

        }
    }
}