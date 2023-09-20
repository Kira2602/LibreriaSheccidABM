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


public class fragmentAgregarCliente extends Fragment {


    EditText txtNombre, txtApellido, txtCorreo, txtCelular;
    Button btnGuarda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_cliente, container, false);

        txtNombre = view.findViewById(R.id.editTextNombre);
        txtApellido = view.findViewById(R.id.editTextApellidos);
        txtCorreo = view.findViewById(R.id.editTextCorreo);
        txtCelular = view.findViewById(R.id.editTextNumeroCelular);
        btnGuarda = view.findViewById(R.id.btnGuardar);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().length() > 0 && txtApellido.getText().toString().length() > 0 && txtCorreo.getText().toString().length() > 0
                        && txtCelular.getText().toString().length() > 0) {
                    DbClientes dbClientes = new DbClientes(getActivity());
                    long id = dbClientes.insertarCliente(txtNombre.getText().toString(), txtApellido.getText().toString(), txtCorreo.getText().toString(), txtCelular.getText().toString());

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
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtCelular.setText("");
    }
}