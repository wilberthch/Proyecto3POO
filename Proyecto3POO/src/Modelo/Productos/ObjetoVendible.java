/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Productos;

import Modelo.Restaurante;

/**
 *
 * @author will
 */
public class ObjetoVendible implements Comparable<ObjetoVendible> {
    
    protected String nombre;
    protected double precio;
    protected double descuento;
    protected String rutaImagen;
    
    public static final String IMAGES_PATH =  "ProductoImagenes/";
    public static final String IMAGEN_DEFAULT =  IMAGES_PATH + "default.png";

    
    
    public ObjetoVendible()
    {
        rutaImagen = IMAGEN_DEFAULT;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        double descuentoTotal = precio * descuento / 100;
        double precioFinal = precio - descuentoTotal;
        return precioFinal;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the descuento
     */
    public double getDescuento() {
        return descuento;
    }
    

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public int compareTo(ObjetoVendible o) {
        
        return nombre.compareTo(o.getNombre());
        
    }
    
    @Override
    public String toString() {
        return "ObjetoVendible{" + "nombre=" + nombre + ", precio=" + precio + ", descuento=" + descuento + '}';
    }
    
}
