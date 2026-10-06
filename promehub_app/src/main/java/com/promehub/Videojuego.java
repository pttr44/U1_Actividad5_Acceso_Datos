package com.promehub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "videojuego")
@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(propOrder = {
    "id",
    "titulo",
    "plataforma",
    "genero",
    "precio",
    "stock",
    "codigoProveedor",
})

public class Videojuego {
    
    private int id;
    private String titulo;
    private String plataforma;
    private String genero;
    private double precio;
    private int stock;
    private int codigoProveedor;

    public Videojuego() {
        //vacío para el unmarshalling
    }

    public Videojuego(int id, String titulo, String plataforma, String genero, double precio, int stock, int codigoProveedor) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
        this.codigoProveedor = codigoProveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Videojuego{");
        sb.append("id=").append(id);
        sb.append(", titulo=").append(titulo);
        sb.append(", plataforma=").append(plataforma);
        sb.append(", genero=").append(genero);
        sb.append(", precio=").append(precio);
        sb.append(", stock=").append(stock);
        sb.append(", codigoProveedor=").append(codigoProveedor);
        sb.append('}');
        return sb.toString();
    }

    
}
