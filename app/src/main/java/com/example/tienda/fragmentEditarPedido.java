package com.example.tienda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.tienda.db.DbPedidos;
import com.example.tienda.entidades.Pedidos;

public class fragmentEditarPedido extends Fragment {
    EditText txtProducto, txtCantidad;
    Button btnEdita;
    Button btnElimina;
    boolean accept = false;



    Pedidos pedidos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_pedido, container, false);

        int bundle = getArguments().getInt("ID");



        txtProducto = view.findViewById(R.id.editTextProducto);
        txtCantidad = view.findViewById(R.id.editTextCantidad);
        btnEdita = view.findViewById(R.id.btnGuardar);
        btnElimina = view.findViewById(R.id.btnEliminar);

        DbPedidos dbPedidos = new DbPedidos(getActivity());
        pedidos = dbPedidos.verPedidos(bundle);

        if(pedidos != null){
            txtProducto.setText(pedidos.getProducto());
            txtCantidad.setText(pedidos.getCantidad());
        }

        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtProducto.getText().toString().length() > 0 && txtCantidad.getText().toString().length() > 0){
                    accept = dbPedidos.editarPedidos(bundle, txtProducto.getText().toString(), txtCantidad.getText().toString());
                }
                else {
                    Toast.makeText(getActivity(),"LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }

                if(accept){
                    Toast.makeText(getActivity(),"EDITADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"OCURRIO UN ERROR AL EDITAR", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Desea eliminar este pedido?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbPedidos.eliminarPedido(bundle)){
                                    regresa();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });


        return view;
    }

    private void regresa(){
        Fragment fragment = new fragment_three();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}