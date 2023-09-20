package com.example.tienda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tienda.db.DbClientes;
import com.example.tienda.db.DbProductos;
import com.example.tienda.entidades.Productos;


public class fragmentEditarProducto extends Fragment {
    EditText txtNombre, txtDescripcion;
    ImageView imageView;
    Button btnSelecionaImagen;
    Button btnEdita;
    Button btnElimina;
    boolean accept = false;
    private static final int PICK_IMAGE_REQUEST = 1;
    Productos productos;
    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_producto, container, false);

        int bundle = getArguments().getInt("ID");

        txtNombre = view.findViewById(R.id.etTitulo);
        txtDescripcion = view.findViewById(R.id.etDescripcion);
        btnSelecionaImagen = view.findViewById(R.id.btnSeleccionarImagen);
        btnEdita = view.findViewById(R.id.btnGuardarImagen);
        btnElimina = view.findViewById(R.id.btnEliminarImagen);

        // Inicializa imageView
        imageView = view.findViewById(R.id.imageView); // Asegúrate de tener un ImageView con el ID "imageView" en tu diseño XML

        DbProductos dbProductos = new DbProductos(getActivity());
        productos = dbProductos.verProductos(bundle);
        txtNombre.setText(productos.getNombre_producto());
        txtDescripcion.setText(productos.getDescripcion());

        btnSelecionaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
            }
        });

        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener la URL de la nueva imagen seleccionada (imageUri) o la imagen por defecto si imageUri es nulo
                String nuevaImagenUrl = (imageUri != null) ? imageUri.toString() : "";

                DbProductos bdProductos = new DbProductos(getActivity());
                boolean id = bdProductos.editarProducto(bundle, txtNombre.getText().toString(), txtDescripcion.getText().toString(), nuevaImagenUrl);

                if (id) {
                    Toast.makeText(getActivity(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }

                // Luego de guardar, muestra la imagen en imageView
                if (imageUri != null) {
                    imageView.setImageURI(imageUri);
                } else {
                    // Coloca la imagen por defecto desde los recursos drawable
                    imageView.setImageResource(R.drawable.resaltador); // Reemplaza "imagen_por_defecto" con el nombre de tu imagen en drawable
                }
            }
        });




        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Desea eliminar este producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbProductos.eliminarProducto(bundle)){
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
        Fragment fragment = new fragment_two();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}