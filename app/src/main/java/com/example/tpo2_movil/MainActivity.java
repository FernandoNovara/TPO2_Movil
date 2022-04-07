package com.example.tpo2_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btIniciar,btFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inicializar();
    }

    private void Inicializar()
    {
        btIniciar = findViewById(R.id.btIniciar);
        btFinalizar = findViewById(R.id.btFinalizar);
        Intent i =new Intent(MainActivity.this, Contador.class);
        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startService(i);
            }
        });
        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(i);
            }
        });

    }
}

