/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbodega;

/**
 *
 * @author Marco_Gall
 */
public class Producto {
    private String nombre;
    private double cantidad;
    private double precio;
    public Producto(String nombre, double cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Cantidad: " + cantidad + " | Precio por unidad: " + precio + " S/.";
    }
}
