package com.example.tienda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tienda.entidades.Pedidos;

import java.util.ArrayList;

public class DbPedidos extends DbHelper {

    Context context;
    public DbPedidos(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long insertarPedido(String producto, String cantidad){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("producto", producto);
            values.put("cantidad", cantidad);

            id = db.insert(TABLA_PEDIDOS, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public ArrayList<Pedidos> mostrarPedidos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Pedidos> listaPedidos =  new ArrayList<>();

        Pedidos pedidos = null;
        Cursor cursorPedidos = null;

        cursorPedidos = db.rawQuery("SELECT * FROM " + TABLA_PEDIDOS, null);

        if (cursorPedidos.moveToFirst()){
            do {
                pedidos = new Pedidos();
                pedidos.setIdPe(cursorPedidos.getInt(0));
                pedidos.setProducto(cursorPedidos.getString(1));
                pedidos.setCantidad(cursorPedidos.getString(2));

                listaPedidos.add(pedidos);
            }while (cursorPedidos.moveToNext());
        }
        cursorPedidos.close();
        return listaPedidos;
    }

    public Pedidos verPedidos(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Pedidos> listaPedidos =  new ArrayList<>();

        Pedidos pedidos = null;
        Cursor cursorPedidos = null;

        System.out.println(id);

        cursorPedidos = db.rawQuery("SELECT * FROM " + TABLA_PEDIDOS + " WHERE idPe= " + id + " LIMIT 1", null);

        if (cursorPedidos.moveToFirst()){
            pedidos = new Pedidos();
            pedidos.setIdPe(cursorPedidos.getInt(0));
            pedidos.setProducto(cursorPedidos.getString(1));
            pedidos.setCantidad(cursorPedidos.getString(2));
        }
        cursorPedidos.close();
        return pedidos;
    }

    public boolean editarPedidos(int id, String producto, String cantidad){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_PEDIDOS + " SET producto = '"+producto+"', cantidad = '"+cantidad+"' WHERE idPe = '"+id+"'");
            c = true;
        }catch (Exception e){
            e.toString();
            c = false;
        } finally {
            db.close();
        }
        return c;
    }

    public boolean eliminarPedido(int id){
        boolean c = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_PEDIDOS + " WHERE idPe = '"+ id +"'");
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