/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Facturacion;

import java.time.LocalDateTime;
import java.util.LinkedList;
import Modelo.Productos.*;
import Modelo.Usuarios.Cajero;

/**
 *
 * @author will
 */
public class Factura 
{
    private static int contadorFacturas = 0;
    private int numFactura;
    private LocalDateTime fecha;
    private LinkedList<ObjetoVendible> items;
    private Cajero cajero;
    
    public Factura(Cajero pCajero)
    {
        contadorFacturas++;
        numFactura = contadorFacturas;
        fecha = LocalDateTime.now();
        items = new LinkedList<>();
        cajero = pCajero;
    }
    
    /**
     * @return the numFactura
     */
    public int getNumFactura() {
        return numFactura;
    }

    /**
     * @return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void agregarObjeto(ObjetoVendible pItem)
    {
        items.add(pItem);
    }
    
    public void removerObjeto(ObjetoVendible pItem)
    {
        items.remove(pItem);
    }
    
    public double getTotal()
    {
        double total = 0.0;
        
        for(ObjetoVendible objeto : items)
        {
            total += objeto.getPrecio();
        }
        
        return total;
    }

    
    
    
    
    
}
