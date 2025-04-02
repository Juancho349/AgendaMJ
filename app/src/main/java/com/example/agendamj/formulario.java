package com.example.agendamj;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formulario extends AppCompatActivity {

    Spinner sp1;
    String documento [] = {"Cedula", "Tarjeta de identidad", "Cedula de extrajeria", "Pasaporte"};
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
    }
    public void enviar(View view){

    }


}