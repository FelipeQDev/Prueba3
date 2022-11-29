package com.example.prueba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class RVArmas extends RecyclerView.Adapter<RVArmas.ViewHolder> {

    Context context;
    List<Armas> listaArmas;
    LayoutInflater inlaterA;


    public RVArmas(Context context, List<Armas> listaArmas) {
        this.context = context;
        this.listaArmas = listaArmas;
        this.inlaterA = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inlaterA.inflate(R.layout.rv_armas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Armas a = listaArmas.get(position);
        holder.txtNombreA.setText(a.nombre);
        holder.txtElemento.setText(a.elemento);
        holder.txtAtaque.setText(a.ataque);
        holder.txtNivel.setText(a.nivel_requerido);

        DocumentReference referencia = a.tipo_arma;

        if (referencia != null){
            referencia.addSnapshotListener((value, error) -> {
                if (value != null) {
                    holder.txtNombreTA.setText(value.get("nombre").toString());
                }
            });
        } else {
            holder.txtNombreTA.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return listaArmas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagenArma;
        TextView txtNombreA, txtElemento, txtAtaque, txtNivel, txtNombreTA;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Referencias
            imagenArma = itemView.findViewById(R.id.imagenArma);
            txtNombreA = itemView.findViewById(R.id.txtNombreA);
            txtElemento = itemView.findViewById(R.id.txtElemento);
            txtAtaque = itemView.findViewById(R.id.txtAtaque);
            txtNivel = itemView.findViewById(R.id.txtNivel);
            txtNombreTA = itemView.findViewById(R.id.txtNombreTA);
        }
    }
}
