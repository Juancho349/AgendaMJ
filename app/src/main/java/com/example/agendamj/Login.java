package com.example.agendamj;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    EditText edtUsuario, edtContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtUsuario = findViewById(R.id.edtUsuario);
        edtContraseña = findViewById(R.id.edtContraseña);
    }

    public void iniciarSesion(){
        String usuario = String.valueOf(edtUsuario.getText());
        String contraseña = edtContraseña.getText().toString();
        if (usuario == "admin"){
            if (contraseña == "admin"){

            }
        }
    }
}