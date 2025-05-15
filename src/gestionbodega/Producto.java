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
    public Producto(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
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
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Cantidad: " + cantidad;
    }
}
