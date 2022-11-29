package com.example.prueba3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivityCartas2 extends AppCompatActivity {

    EditText txtNombreC;
    EditText txtBonificacion;
    EditText txtPeso;
    EditText txtDescripcion;
    Button btnGuardarCarta;
    RecyclerView rvCartas;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Cartas> datos = new ArrayList<>();
    RVCartas adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas2);

        txtNombreC = findViewById(R.id.txtNombreC);
        txtBonificacion = findViewById(R.id.txtBonificacion);
        txtPeso = findViewById(R.id.txtPeso);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        btnGuardarCarta =findViewById(R.id.btnGuardarCarta);
        rvCartas = findViewById(R.id.rvCartas);

        cargarDatosFirestore();

        btnGuardarCarta.setOnClickListener(view -> {
            Map<String, Object> cartaMap = new HashMap<>();
            cartaMap.put("nombre", txtNombreC.getText().toString());
            cartaMap.put("bonificacion", Integer.parseInt(txtBonificacion.getText().toString()));
            cartaMap.put("peso", Integer.parseInt(txtPeso.getText().toString()));
            cartaMap.put("descripcion", txtDescripcion.getText().toString());


            db.collection("CARTAS")
                    .add(cartaMap)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Carta Insertada", Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }

    private void cargarDatosFirestore() {
        db.collection("CARTAS")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documento : task.getResult()){
                            Cartas c = documento.toObject(Cartas.class);
                            datos.add(c);
                        }

                        adapter = new RVCartas(this, datos);
                        rvCartas.setAdapter(adapter);
                        rvCartas.setHasFixedSize(true);
                        rvCartas.setLayoutManager(new LinearLayoutManager(this));
                    }
                });
    }
}