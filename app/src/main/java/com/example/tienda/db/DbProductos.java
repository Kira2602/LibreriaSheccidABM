package com.example.tienda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tienda.entidades.Clientes;
import com.example.tienda.entidades.Productos;

import java.util.ArrayList;

public class DbProductos extends DbHelper{
    Context context;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(String nombre_producto, String descripcion, String urlImagen){
        long idP = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_producto", nombre_producto);
            values.put("descripcion", descripcion);
            values.put("imagen", urlImagen); // Asegúrate de que "imagen" contenga una URL o una referencia a la imagen

            idP = db.insert(TABLA_PRODUCTOS, null, values);
        }catch (Exception e){
            e.printStackTrace(); // Mejor usar e.printStackTrace() para ver detalles del error en el registro
        }
        return idP;
    }


    public ArrayList<Productos> mostrarProductos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos =  new ArrayList<>();

        Productos productos = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS, null);

        if (cursorProductos.moveToFirst()){
            do {
                productos = new Productos();
                productos.setIdP(cursorProductos.getInt(0));
                productos.setNombre_producto(cursorProductos.getString(1));
                productos.setDescripcion(cursorProductos.getString(2));
                productos.setImagen(cursorProductos.getString(3));

                listaProductos.add(productos);
            }while (cursorProductos.moveToNext());
        }
        cursorProductos.close();
        return listaProductos;
    }

    public Productos verProductos(int idP){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos =  new ArrayList<>();

        Productos productos = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS + " WHERE idP= " + idP + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()){
            productos = new Productos();
            productos.setIdP(cursorProductos.getInt(0));
            productos.setNombre_producto(cursorProductos.getString(1));
            productos.setDescripcion(cursorProductos.getString(2));
            productos.setImagen(cursorProductos.getString(3));

        }
        cursorProductos.close();
        return productos;
    }

    public boolean editarProducto(int idP, String nombre_producto, String descripcion, String imagen){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_PRODUCTOS + " SET nombre_producto = '"+nombre_producto+"', descripcion = '"+descripcion+"', imagen = '"+imagen+"' WHERE idP = '"+idP+"'");
            c = true;
        }catch (Exception e){
            e.toString();
            c = false;
        } finally {
            db.close();
        }
        return c;
    }

    public boolean eliminarProducto(int idP){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_PRODUCTOS + " WHERE idP = '"+ idP +"'");
            c = true;
        }catch (Exception e){
            e.toString();
            c = false;
        } finally {
            db.close();
        }
        return c;
    }
}
