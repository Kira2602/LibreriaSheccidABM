package com.example.tienda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private  static  final  int DATABASE_VERSION = 2;
    private  static  final  String DATABASE_NOMBRE = "tienda.db";
    public  static  final  String TABLA_USUARIOS = "t_usuarios";
    public  static  final  String TABLA_CLIENTES = "t_clientes";
    public  static  final  String TABLA_PEDIDOS = "t_pedidos";
    public  static  final  String TABLA_PRODUCTOS = "t_productos";



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CLIENTES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "correo TEXT," +
                "celular TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PRODUCTOS + "(" +
                "idP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_producto TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "imagen TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_USUARIOS + "(" +
                "idU INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PEDIDOS + "(" +
                "idPe INTEGER PRIMARY KEY AUTOINCREMENT," +
                "producto TEXT NOT NULL," +
                "cantidad TEXT NOT NULL)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_PEDIDOS);
        onCreate(sqLiteDatabase);
    }
}
