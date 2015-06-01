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
public class Producto extends ObjetoVendible
{
    
    protected String rutaImagen;
    
    public Producto()
    {
        rutaImagen=Restaurante.IMAGEN_DEFAULT;
    }

    public Producto(Producto pProducto)
    {
        nombre = pProducto.getNombre();
        precio = pProducto.getPrecio();
        descuento = pProducto.getDescuento();
        rutaImagen = pProducto.getRutaImagen();
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
}
