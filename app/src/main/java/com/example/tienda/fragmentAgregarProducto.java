package com.example.tienda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tienda.db.DbClientes;
import com.example.tienda.db.DbProductos;

public class fragmentAgregarProducto extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri;
    private ImageView imageView;
    private String rutaImagen;
    EditText txtNombre, txtDescripcion;
    private Button btnSeleccionarImagen, btnGuarda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

        txtNombre = view.findViewById(R.id.etTitulo);
        txtDescripcion = view.findViewById(R.id.etDescripcion);

        imageView = view.findViewById(R.id.imageView);
        btnSeleccionarImagen = view.findViewById(R.id.btnSeleccionarImagen);
        btnGuarda = view.findViewById(R.id.btnGuardarImagen);
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbProductos bdProductos = new DbProductos(getActivity());

                // Utiliza la ruta de la imagen (rutaImagen) en lugar de una cadena vacía en el siguiente código
                long id = bdProductos.insertarProducto(txtNombre.getText().toString(), txtDescripcion.getText().toString(), rutaImagen);

                if (id > 0) {
                    Toast.makeText(getActivity(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(getActivity(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void limpiar(){
        txtNombre.setText("");
        txtDescripcion.setText("");
        imageView.setImageURI(Uri.parse(""));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

            // Guarda la URL de la imagen en el objeto Productos
            rutaImagen = imageUri.toString();
        }
    }

}
