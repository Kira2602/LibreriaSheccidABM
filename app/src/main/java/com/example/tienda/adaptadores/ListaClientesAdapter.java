package com.example.tienda.adaptadores;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.R;
import com.example.tienda.entidades.Clientes;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {
    ArrayList<Clientes> listaClientes;

    public interface OnItemClickListener {
        void onItemClick(int nombre);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ListaClientesAdapter(ArrayList<Clientes> listaClientes){
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null, false);
        return new ClienteViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, final int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
        holder.viewApellido.setText(listaClientes.get(position).getApellido());
        holder.viewCorreo.setText(listaClientes.get(position).getCorreo());
        holder.viewCelular.setText(listaClientes.get(position).getCelular());


    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido, viewCorreo, viewCelular;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewCelular = itemView.findViewById(R.id.viewCelular);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(listaClientes.get(getAdapterPosition()).getId());

                    }
                }
            });

        }
    }
}
