package com.example.agendamj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculadora extends AppCompatActivity {
    TextView textResultado, textOperacion, tvHistorial;
    String operacion = "";
    double numero1 = 0 , numero2 = 0;
    boolean validadorNumero= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textResultado = findViewById(R.id.TextResult);
        textOperacion = findViewById(R.id.textOperacion);
        tvHistorial = findViewById(R.id.tvHistorial);
    }

    public void asignarNumero (View view){
        if (validadorNumero){
            textResultado.setText("");
            validadorNumero = false;
        }
        Button botonPresionado = (Button) view;
        String numero = botonPresionado.getText().toString();
        String numeroActual = textResultado.getText().toString();
        if (numero.equals(".")){
            if (numeroActual.endsWith(".") || numeroActual.contains(".")){
                return;
            }
        if (numeroActual.isEmpty()){
            textResultado.setText("0");
        }
        }
        textResultado.append(numero);
    }

    public void asignarOperador (View view){
        Button botonPresionado = (Button) view;
        operacion = botonPresionado.getText().toString();
        numero1 = Double.parseDouble(textResultado.getText().toString());
        if (numero1 % 1 == 0){
            textOperacion.setText(String.valueOf((int) numero1) + " " + operacion);
        }else{
            textOperacion.setText(numero1 + " " + operacion);
        }

        validadorNumero = true;
    }

    public void calcular (View view){
        numero2 = Double.parseDouble(textResultado.getText().toString());
        double resultadoOperacion= 0;
        switch (operacion) {
            case "+":
                resultadoOperacion = numero1 + numero2;
                break;
            case "-":
                resultadoOperacion = numero1 - numero2;
                break;
            case "x":
                resultadoOperacion = numero1 * numero2;
                break;
            case "/":
                resultadoOperacion = (numero2 != 0) ? numero1 / numero2 : 0;
                break;
        }
        String num1Str = (numero1 % 1 == 0) ? String.valueOf((int) numero1) : String.valueOf(numero1);
        String num2Str = (numero2 % 1 == 0) ? String.valueOf((int) numero2) : String.valueOf(numero2);
        String resultadoStr = (resultadoOperacion % 1 == 0) ? String.valueOf((int) resultadoOperacion) : String.valueOf(resultadoOperacion);

        textOperacion.setText(num1Str + " " + operacion + " " + num2Str + " =");

        textResultado.setText(resultadoStr);

        String historialActual = tvHistorial.getText().toString();
        String nuevaLinea = num1Str + " " + operacion + " " + num2Str + " = " + resultadoStr;
        tvHistorial.setText(nuevaLinea + "\n" + historialActual);
        validadorNumero = true;
    }

    public void borrarGeneral (View view){
        textResultado.setText("");
        textOperacion.setText("");
        numero1 = 0;
        numero2 = 0;
        operacion = "";
        validadorNumero = false;
    }
    public void borrarUltimoDigito(View view) {
        String textoActual = textResultado.getText().toString();

        if (!textoActual.isEmpty()) {
            textoActual = textoActual.substring(0, textoActual.length() - 1);
            textResultado.setText(textoActual);
        }
    }
}