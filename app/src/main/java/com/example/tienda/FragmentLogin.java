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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tienda.db.DbUsuarios;


public class FragmentLogin extends Fragment {
    Button btnLogin,btnRegister;
    EditText edtEmail,edtPassword;

    CallBackFragment callBackFragment;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length() > 0){
                    DbUsuarios dbUsuarios = new DbUsuarios(getActivity());
                    if(dbUsuarios.validarUsuario(edtEmail.getText().toString(),edtPassword.getText().toString())){
                        limpiar();
                        Intent i = new Intent(getActivity(), MainActivity2.class);
                        startActivity(i);

                    }else {
                        Toast.makeText(getActivity(), "VERIFIQUE SUS DATOS", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "LLENE SUS DATOS", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBackFragment!=null){
                    callBackFragment.changeFragment();
                }
            }
        });

        return view;
    }



    public void setCallBackFragment(CallBackFragment callBackFragment){
        this.callBackFragment = callBackFragment;
    }

    public void limpiar(){
        edtEmail.setText("");
        edtPassword.setText("");
    }

}
