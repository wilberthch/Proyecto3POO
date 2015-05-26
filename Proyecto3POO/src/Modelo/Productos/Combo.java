/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Productos;

import java.util.LinkedList;

/**
 *
 * @author will
 */
public class Combo extends ObjetoVendible implements iAgrandable {
    
    LinkedList<Producto> productos;
    
    
    private static final double porcentajeDescuentoCombo = 0.85;
    
    public Combo()
    {
        
        productos = new LinkedList<>();
    }
    
    public void agregarProducto(Producto pProducto)
    {
        productos.add(pProducto);
    }
    
    public void removerProducto(Producto pProducto)
    {
        productos.remove(pProducto);
    }
    
    @Override
    public double getPrecio()
    {
        double precioNeto;
        
        double precioBruto = 0.0;
        
        for(Producto producto : productos)
        {
            precioBruto += producto.getPrecio();
        }
        
        precioNeto = precioBruto * porcentajeDescuentoCombo;
        
        return precioNeto;
    }
    
    @Override
    public void setPrecio(double precio) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agrandar() throws Exception {
        for(Producto producto : productos)
        {
            if( producto instanceof ProductoAgrandable)
            {
                ((ProductoAgrandable)producto).agrandar();
            }
        }
    }

    @Override
    public void reducir() throws Exception {
        
        for(Producto producto : productos)
        {
            if( producto instanceof ProductoAgrandable)
            {
                ((ProductoAgrandable)producto).reducir();
            }
        }
        
    }

    
}
