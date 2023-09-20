package com.example.tienda.adaptadores;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.R;
import com.example.tienda.entidades.Pedidos;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.PedidoViewHolder> {
    ArrayList<Pedidos> listaPedidos;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ListaPedidosAdapter(ArrayList<Pedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, final int position) {
        holder.viewProducto.setText(listaPedidos.get(position).getProducto());
        holder.viewCantidad.setText(listaPedidos.get(position).getCantidad());
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {

        TextView viewProducto, viewCantidad;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewProducto = itemView.findViewById(R.id.viewProducto);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(listaPedidos.get(getAdapterPosition()).getIdPe());
                    }
                }
            });
        }
    }
}

