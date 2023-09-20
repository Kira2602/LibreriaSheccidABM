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

import com.example.tienda.db.DbClientes;
import com.example.tienda.entidades.Clientes;


public class fragmentEditarCliente extends Fragment {
    EditText txtNombre, txtApellido, txtCorreo, txtCelular;
    Button btnEdita;
    Button btnElimina;
    boolean accept = false;



    Clientes clientes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_cliente, container, false);

        int bundle = getArguments().getInt("ID");



        txtNombre = view.findViewById(R.id.editTextNombre);
        txtApellido = view.findViewById(R.id.editTextApellidos);
        txtCorreo = view.findViewById(R.id.editTextCorreo);
        txtCelular = view.findViewById(R.id.editTextNumeroCelular);
        btnEdita = view.findViewById(R.id.btnGuardar);
        btnElimina = view.findViewById(R.id.btnEliminar);

        DbClientes dbClientes = new DbClientes(getActivity());
        clientes = dbClientes.verClientes(bundle);

        if(clientes != null){
            txtNombre.setText(clientes.getNombre());
            txtApellido.setText(clientes.getApellido());
            txtCorreo.setText(clientes.getCorreo());
            txtCelular.setText(clientes.getCelular());
        }

        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNombre.getText().toString().length() > 0 && txtApellido.getText().toString().length() > 0 && txtCorreo.getText().toString().length() > 0
                && txtCelular.getText().toString().length() > 0){
                    accept = dbClientes.editarCliente(bundle, txtNombre.getText().toString(), txtApellido.getText().toString(), txtCorreo.getText().toString(), txtCelular.getText().toString());
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
                builder.setMessage("¿Desea eliminar este cliente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbClientes.eliminarCliente(bundle)){
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
        Fragment fragment = new fragment_one();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}