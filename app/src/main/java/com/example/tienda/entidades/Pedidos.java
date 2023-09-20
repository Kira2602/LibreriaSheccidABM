package com.example.tienda.entidades;

public class Pedidos {
    private int idPe;
    private String producto;
    private String cantidad;


    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public int getIdPe() {
        return idPe;
    }

    public void setIdPe(int idPe) {
        this.idPe = idPe;
    }
}