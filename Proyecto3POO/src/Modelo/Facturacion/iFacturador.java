package Modelo.Facturacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Modelo.Facturacion.Factura;

/**
 *
 * @author will
 */
public interface iFacturador {
    
    Factura getFacturaPorId(int pId) throws NullPointerException;
    void agregarFactura(Factura pFactura);
    void removerFactura(Factura pFactura);
    
    
}
