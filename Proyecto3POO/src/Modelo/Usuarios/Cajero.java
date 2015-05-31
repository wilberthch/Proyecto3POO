/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

import Modelo.Facturacion.Factura;
import Modelo.Facturacion.iFacturador;
import Modelo.Productos.ObjetoVendible;
import java.util.LinkedList;


/**
 *
 * @author will
 */
public class Cajero extends Usuario
{
    iFacturador restaurante;
    
    public Cajero(iFacturador pRestaurante)
    {
        restaurante = pRestaurante;
    }
    public Cajero()
    {
        
    }
    
    public void guardarFactura(Factura pFactura) throws NullPointerException
    {
        LinkedList<ObjetoVendible> items;
        Factura factura = null;
        try
        {
            items = pFactura.getItems();
        }
        catch(NullPointerException ex)
        {
            throw new NullPointerException("parametro nulo");
        }
        
        try
        {
            factura = restaurante.getFacturaPorId(pFactura.getNumFactura());
            factura.setItems(items);
        }
        catch(NullPointerException ex)
        {
            factura = new Factura(this, items);
            restaurante.agregarFactura(factura);
        }
    }
    
    
    
}
