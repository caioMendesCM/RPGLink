package com.example.rpglink.Adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rpglink.R;
import java.util.List;

public class RpgListAdapter extends RecyclerView.Adapter<RpgListAdapter.MyViewHolder> {

    private List<String> titulos;

    public RpgListAdapter(List<String> titulos) {
        this.titulos = titulos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.rpg_list_view_holder,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String titulo = titulos.get(position);
        holder.titulo.setText(titulo);
    }

    @Override
    public int getItemCount() {
        return titulos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.rpgListBtn);
            titulo.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(),"exo.ttf"));
            titulo.setPadding(48,0,48,0);

        }
    }
}
