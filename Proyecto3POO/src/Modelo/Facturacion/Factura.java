/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Facturacion;

import Modelo.Productos.ObjetoVendible;
import java.time.LocalDateTime;
import Modelo.Usuarios.Cajero;
import java.util.LinkedList;

/**
 *
 * @author will
 */
public class Factura 
{
    private static int contadorFacturas = 0;
    private final int numFactura;
    private final LocalDateTime fecha;
    private LinkedList<ObjetoVendible> items;
    private final Cajero cajero;
    private EstadoFactura estado;
    
    public Factura(Cajero pCajero)
    {
        contadorFacturas++;
        numFactura = contadorFacturas;
        fecha = LocalDateTime.now();
        items = new LinkedList<>();
        cajero = pCajero;
        estado = EstadoFactura.CREADA;
    }
    
    public Factura(Cajero pCajero, LinkedList<ObjetoVendible> pItems)
    {
        contadorFacturas++;
        numFactura = contadorFacturas;
        fecha = LocalDateTime.now();
        items = pItems;
        cajero = pCajero;
        estado = EstadoFactura.CREADA;
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
        getItems().add(pItem);
    }
    
    public void removerObjeto(ObjetoVendible pItem)
    {
        getItems().remove(pItem);
    }
    
    public double getTotal()
    {
        double total = 0.0;
        
        for(ObjetoVendible objeto : getItems())
        {
            total += objeto.getPrecio();
        }
        
        return total;
    }
    
    public void setFacturaCancelada()
    {
        estado = EstadoFactura.CANCELADA;
    }
    
    public void setFacturaPagada()
    {
        estado = EstadoFactura.PAGADA;
    }
    public Cajero getCajero(){
        return cajero;
    }

    /**
     * @return the items
     */
    public LinkedList<ObjetoVendible> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(LinkedList<ObjetoVendible> items) {
        this.items = items;
    }
    

    
    
    
    
    
}
