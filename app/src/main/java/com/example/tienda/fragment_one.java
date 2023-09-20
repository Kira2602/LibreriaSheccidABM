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
import com.example.tienda.db.DbClientes;
import com.example.tienda.entidades.Clientes;

import java.util.ArrayList;


public class fragment_one extends Fragment implements  ListaClientesAdapter.OnItemClickListener{
    Button btnCambiarFragment;
    RecyclerView listaClientes;
    ArrayList<Clientes>listaArrayClientes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        btnCambiarFragment = view.findViewById(R.id.btnAgregar);

        btnCambiarFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragmentAgregarCliente(); // Reemplaza "OtroFragment" con el nombre de tu otro fragmento
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment); // Reemplaza "contenedorFragment" con el ID del contenedor donde deseas mostrar el otro fragmento
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        listaClientes = view.findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(getActivity()));

        DbClientes dbClientes = new DbClientes(getActivity());

        listaArrayClientes = new ArrayList<>();

        ListaClientesAdapter adapter = new ListaClientesAdapter(dbClientes.mostrarClientes());
        listaClientes.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        Fragment fragment = new fragmentEditarCliente();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();


    }
}