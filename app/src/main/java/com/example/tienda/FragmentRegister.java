package com.example.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tienda.db.DbClientes;
import com.example.tienda.db.DbUsuarios;

public class FragmentRegister extends Fragment {
    Button btnLogin,btnRegister;
    EditText edtEmail,edtPassword, edtPasswordC;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtPasswordC = view.findViewById(R.id.edtPasswordC);

        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length() > 0 && edtPasswordC.getText().toString().length() > 0) {
                    if(edtPassword.getText().toString().equals(edtPasswordC.getText().toString())) {
                        DbUsuarios dbUsuarios = new DbUsuarios(getActivity());
                        long id = dbUsuarios.insertarUsuario(edtEmail.getText().toString(), edtPassword.getText().toString());
                        if (id > 0) {
                            Toast.makeText(getActivity(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            limpiar();
                            Intent i = new Intent(getActivity(), MainActivity2.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(getActivity(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),"LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_LONG).show();}

                }
                else {
                    Toast.makeText(getActivity(),"LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    return view;
    }
    public void limpiar(){
        edtEmail.setText("");
        edtPassword.setText("");
        edtPasswordC.setText("");
    }
}
