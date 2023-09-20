package com.example.tienda;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tienda.db.DbClientes;
import com.example.tienda.db.DbPedidos;


public class fragmentAgregarPedido extends Fragment {


    EditText txtProducto, txtCantidad;
    Button btnGuarda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_pedido, container, false);

        txtProducto = view.findViewById(R.id.editTextProducto);
        txtCantidad = view.findViewById(R.id.editTextCantidad);
        btnGuarda = view.findViewById(R.id.btnGuardar);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtProducto.getText().toString().length() > 0 && txtCantidad.getText().toString().length() > 0) {
                    DbPedidos dbPedidos = new DbPedidos(getActivity());
                    long id = dbPedidos.insertarPedido(txtProducto.getText().toString(), txtCantidad.getText().toString());

                    if (id > 0) {
                        Toast.makeText(getActivity(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(getActivity(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void limpiar(){
        txtProducto.setText("");
        txtCantidad.setText("");
    }
}