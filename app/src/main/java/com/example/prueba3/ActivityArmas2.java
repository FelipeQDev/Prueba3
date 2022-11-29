package com.example.prueba3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityArmas2 extends AppCompatActivity {

    EditText txtNombreA;
    EditText txtElemento;
    EditText txtAtaque;
    EditText txtNivel;

    Spinner spTipoArma;
    Button btnGuardarAr;

    List<Armas> datos = new ArrayList<>();
    RVArmas adapter;

    RecyclerView rvArmas;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas2);

        txtNombreA = findViewById(R.id.txtNombreA);
        txtElemento = findViewById(R.id.txtElemento);
        txtAtaque = findViewById(R.id.txtAtaque);
        txtNivel = findViewById(R.id.txtNivel);

        spTipoArma = findViewById(R.id.spTipoArma);
        btnGuardarAr = findViewById(R.id.btnGuardarAr);
        rvArmas = findViewById(R.id.rvArmas);
        
        cargarDatos();
        
        btnGuardarAr.setOnClickListener(view -> {
            Map<String, Object> armaMap = new HashMap<>();
            armaMap.put("nombre",txtNombreA.getText().toString());
            armaMap.put("elemento",txtElemento.getText().toString());
            armaMap.put("ataque", Integer.parseInt(txtAtaque.getText().toString()));
            SpinnerData spinner_seleccionado = (SpinnerData) spTipoArma.getSelectedItem();
            armaMap.put("tipo_arma", spinner_seleccionado.referencia);
            armaMap.put("nivel", Integer.parseInt(txtNivel.getText().toString()));


            db.collection("ARMAS")
                    .add(armaMap)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Arma Registrada", Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }

    private void cargarDatos() {
        db.collection("TIPO_ARMA")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<SpinnerData> tipoArmaSpiner = new ArrayList<>();
                        for (QueryDocumentSnapshot documento : task.getResult()){
                            SpinnerData spinnerData = new SpinnerData(
                                    documento.getReference(),
                                    String.valueOf(documento.get("nombre"))
                            );
                            tipoArmaSpiner.add(spinnerData);
                        }
                        ArrayAdapter<SpinnerData> adapterSniper = new ArrayAdapter<>(
                                this, android.R.layout.simple_spinner_dropdown_item, tipoArmaSpiner);
                        spTipoArma.setAdapter(adapterSniper);
                    }
                });

        db.collection("ARMAS")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documento : task.getResult()){
                            Armas armas = documento.toObject(Armas.class);
                            datos.add(armas);
                        }

                        adapter = new RVArmas(this, datos);
                        rvArmas.setAdapter(adapter);
                        rvArmas.setHasFixedSize(true);
                        rvArmas.setLayoutManager(new LinearLayoutManager(this));
                    }
                });
    }
}