package com.example.tienda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper{
    Context context;
    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarUsuario(String email, String password){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", password);

            id = db.insert(TABLA_USUARIOS, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public boolean validarUsuario(String email, String password) {
        boolean isValid = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = { "email", "password" };
            String selection = "email = ? AND password = ?";
            String[] selectionArgs = { email, password };

            Cursor cursor = db.query(TABLA_USUARIOS, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                isValid = true;
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }


}
