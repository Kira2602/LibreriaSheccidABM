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

import com.example.tienda.adaptadores.ListaPedidosAdapter;
import com.example.tienda.db.DbPedidos;
import com.example.tienda.entidades.Pedidos;

import java.util.ArrayList;

public class fragment_three extends Fragment implements  ListaPedidosAdapter.OnItemClickListener {

    Button btnCambiarFragment;
    RecyclerView listaPedidos;
    ArrayList<Pedidos> listaArrayPedidos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        btnCambiarFragment = view.findViewById(R.id.btnAgregar);

        btnCambiarFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragmentAgregarPedido(); // Reemplaza "OtroFragment" con el nombre de tu otro fragmento
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment); // Reemplaza "contenedorFragment" con el ID del contenedor donde deseas mostrar el otro fragmento
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        listaPedidos = view.findViewById(R.id.listaPedidos);
        listaPedidos.setLayoutManager(new LinearLayoutManager(getActivity()));

        DbPedidos dbPedidos = new DbPedidos(getActivity());

        listaArrayPedidos = new ArrayList<>();

        ListaPedidosAdapter adapter = new ListaPedidosAdapter(dbPedidos.mostrarPedidos());
        listaPedidos.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        Fragment fragment = new fragmentEditarPedido();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}