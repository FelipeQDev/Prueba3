package com.example.prueba3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnArmas;
    Button btnCartas;
    Button btnTipoArmas;

    Button btnPagina;
    Button btnWiki;
    Button btnDiscord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPagina = findViewById(R.id.btnPagina);
        btnWiki = findViewById(R.id.btnWiki);
        btnDiscord = findViewById(R.id.btnDiscord);

        btnArmas = findViewById(R.id.btnArmas);
        btnCartas = findViewById(R.id.btnCartas);
        btnTipoArmas = findViewById(R.id.btnTipoArmas);

        btnArmas.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityArmas2.class);
            startActivity(intent);
        });

        btnCartas.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityCartas2.class);
            startActivity(intent);
        });

        btnTipoArmas.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityTipoArma2.class);
            startActivity(intent);
        });


        btnPagina.setOnClickListener(view -> {
            Uri uri = Uri.parse(("https://hanabi-ro.com/"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        });

        btnWiki.setOnClickListener(view -> {
            Uri uri = Uri.parse(("https://wiki.hanabi-ro.com/index.php?title=P%C3%A1gina_principal"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        btnDiscord.setOnClickListener(view -> {
            Uri uri = Uri.parse(("https://discord.com/invite/SUYzaYn5D6"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

    }
}