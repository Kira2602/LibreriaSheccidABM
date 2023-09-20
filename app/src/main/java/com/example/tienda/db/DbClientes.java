package com.example.tienda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tienda.entidades.Clientes;

import java.util.ArrayList;

public class DbClientes extends DbHelper {

    Context context;
    public DbClientes(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long insertarCliente(String nombre, String apellido, String correo, String celular){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("correo", correo);
            values.put("celular", celular);

            id = db.insert(TABLA_CLIENTES, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public ArrayList<Clientes> mostrarClientes(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes =  new ArrayList<>();

        Clientes clientes = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLA_CLIENTES, null);

        if (cursorClientes.moveToFirst()){
            do {
                clientes = new Clientes();
                clientes.setId(cursorClientes.getInt(0));
                clientes.setNombre(cursorClientes.getString(1));
                clientes.setApellido(cursorClientes.getString(2));
                clientes.setCorreo(cursorClientes.getString(3));
                clientes.setCelular(cursorClientes.getString(4));

                listaClientes.add(clientes);
            }while (cursorClientes.moveToNext());
        }
        cursorClientes.close();
        return listaClientes;
    }

    public Clientes verClientes(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes =  new ArrayList<>();

        Clientes clientes = null;
        Cursor cursorClientes = null;

        System.out.println(id);

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLA_CLIENTES + " WHERE id= " + id + " LIMIT 1", null);

        if (cursorClientes.moveToFirst()){
                clientes = new Clientes();
                clientes.setId(cursorClientes.getInt(0));
                clientes.setNombre(cursorClientes.getString(1));
                clientes.setApellido(cursorClientes.getString(2));
                clientes.setCorreo(cursorClientes.getString(3));
                clientes.setCelular(cursorClientes.getString(4));

        }
        cursorClientes.close();
        return clientes;
    }

    public boolean editarCliente(int id, String nombre, String apellido, String correo, String celular){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_CLIENTES + " SET nombre = '"+nombre+"', apellido = '"+apellido+"', correo = '"+correo+"', celular = '"+celular+"' WHERE id = '"+id+"'");
            c = true;
        }catch (Exception e){
            e.toString();
            c = false;
        } finally {
            db.close();
        }
        return c;
    }

    public boolean eliminarCliente(int id){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_CLIENTES + " WHERE id = '"+ id +"'");
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
