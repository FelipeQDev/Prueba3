package com.example.prueba3;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
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

public class ActivityTipoArma2 extends AppCompatActivity {

    TextView txtNombreTipoArma;
    Button btnGuardarTA;
    RecyclerView rvTiposdeArmas;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<TipoArma> datos = new ArrayList<>();
    RVTipoArmas adapterTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_arma2);

        txtNombreTipoArma = findViewById(R.id.txtNombreTipoArma);
        btnGuardarTA = findViewById(R.id.btnGuardarTA);
        rvTiposdeArmas = findViewById(R.id.rvTiposdeArmas);

        cargarDatos();

        btnGuardarTA.setOnClickListener(view -> {
            if (TextUtils.isEmpty(txtNombreTipoArma.getText().toString())){
                Toast.makeText(this,"Ingrse los datos", Toast.LENGTH_SHORT).show();
            }else {
                Map<String, Object> dato = new HashMap<>();
                dato.put("nombre", txtNombreTipoArma.getText().toString());

                db.collection("TIPO_ARMA")
                        .add(dato)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Tipo arma Registrada", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void cargarDatos() {
        db.collection("TIPO_ARMA")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documento : task.getResult()) {
                            TipoArma ta = documento.toObject(TipoArma.class);
                            ta.id = documento.getId();
                            datos.add(ta);
                        }

                        adapterTA = new RVTipoArmas(this, datos);
                        rvTiposdeArmas.setAdapter(adapterTA);
                        rvTiposdeArmas.setHasFixedSize(true);
                        rvTiposdeArmas.setLayoutManager(new LinearLayoutManager(this));
                    }
                });
    }
}