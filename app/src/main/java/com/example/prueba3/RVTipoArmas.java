package com.example.prueba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class RVTipoArmas extends RecyclerView.Adapter<RVTipoArmas.ViewHolder> {
    Context context;
    List<TipoArma> listaTipoArma;

    public RVTipoArmas(Context context, List<TipoArma> listaTipoArma) {
        this.context = context;
        this.listaTipoArma = listaTipoArma;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_tipo_armas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipoArma ta = listaTipoArma.get(position);
        holder.tvTipoArma.setText(ta.nombre);
    }

    @Override
    public int getItemCount() {
        return listaTipoArma.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTipoArma;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipoArma = itemView.findViewById(R.id.txtNombreTipoArma);
        }
    }
}
