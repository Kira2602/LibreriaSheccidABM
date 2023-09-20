package com.example.tienda.adaptadores;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.R;
import com.example.tienda.entidades.Productos;
import com.example.tienda.fragment_two;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductosViewHolder> {
    ArrayList<Productos> listaProductos;

    public interface OnItemClickListener {
        void onItemClick(int nombre);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, parent, false);

        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, final int position) {
        holder.viewNombre.setText(listaProductos.get(position).getNombre_producto());

        // Aquí configuramos la imagen del producto
        String imageUrl = listaProductos.get(position).getImagen(); // Utiliza el nombre de la propiedad real en tu objeto Productos

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl) // Carga la imagen desde la URL
                    .into(holder.imageView); //
        } else {
            // Si la URL de la imagen es nula o vacía, puedes configurar una imagen de marcador de posición o mostrar un mensaje en su lugar.
            holder.imageView.setImageResource(R.drawable.resaltador); // Reemplaza con una imagen de marcador de posición adecuada
        }

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDescripcion;
        ImageView imageView;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            imageView = itemView.findViewById(R.id.imageView); // Cambia 'v' a 'imageView'

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(listaProductos.get(getAdapterPosition()).getIdP());
                    }
                }
            });
        }
    }

}
