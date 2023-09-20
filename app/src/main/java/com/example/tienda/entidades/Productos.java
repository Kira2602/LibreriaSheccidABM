package com.example.tienda.entidades;

import android.net.Uri;

public class Productos {
    private int idP;
    private String nombre_producto;
    private String descripcion;
    private String urlImagen;

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return urlImagen;
    }

    public void setImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
