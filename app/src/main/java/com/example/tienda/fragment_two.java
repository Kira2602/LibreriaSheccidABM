package com.example.tienda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda.adaptadores.ListaClientesAdapter;
import com.example.tienda.adaptadores.ListaProductosAdapter;
import com.example.tienda.db.DbClientes;
import com.example.tienda.db.DbProductos;
import com.example.tienda.entidades.Clientes;
import com.example.tienda.entidades.Productos;

import java.util.ArrayList;

public class fragment_two extends Fragment implements  ListaProductosAdapter.OnItemClickListener {

    Button btnCambiarFragment;
    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_two, container, false);
        btnCambiarFragment = view.findViewById(R.id.btnAgregar);

        btnCambiarFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragmentAgregarProducto(); // Reemplaza "OtroFragment" con el nombre de tu otro fragmento
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment); // Reemplaza "contenedorFragment" con el ID del contenedor donde deseas mostrar el otro fragmento
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        listaProductos = view.findViewById(R.id.listaProductos);
        listaProductos.setLayoutManager(new LinearLayoutManager(getActivity()));

        DbProductos dbProductos = new DbProductos(getActivity());

        listaArrayProductos = new ArrayList<>();

        ListaProductosAdapter adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        Fragment fragment = new fragmentEditarProducto();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}