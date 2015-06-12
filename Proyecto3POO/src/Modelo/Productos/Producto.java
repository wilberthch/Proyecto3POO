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
    
    
    
    public Producto()
    {
        super();
        nombre = "nombre";
    }

    public Producto(Producto pProducto)
    {
        
        nombre = pProducto.getNombre();
        precio = pProducto.getPrecio();
        descuento = pProducto.getDescuento();
        rutaImagen = pProducto.getRutaImagen();
    }

    
}
