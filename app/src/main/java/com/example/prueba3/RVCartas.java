package com.example.prueba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVCartas extends RecyclerView.Adapter<RVCartas.ViewHolder>{

    Context context;
    List<Cartas> listaCartas;

    public RVCartas(Context context, List<Cartas> listaCartas) {
        this.context = context;
        this.listaCartas = listaCartas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_cartas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cartas c = listaCartas.get(position);
        holder.txtNombreC.setText(c.nombre);
        holder.txtBonificacion.setText(c.bonificacion);
        holder.txtPeso.setText(c.peso);
        holder.txtDescripcion.setText(c.descripcion);

    }

    @Override
    public int getItemCount() {
        return listaCartas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreC;
        TextView txtBonificacion;
        TextView txtPeso;
        TextView txtDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreC = itemView.findViewById(R.id.txtNombreC);
            txtBonificacion = itemView.findViewById(R.id.txtBonificacion);
            txtPeso = itemView.findViewById(R.id.txtPeso);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}
