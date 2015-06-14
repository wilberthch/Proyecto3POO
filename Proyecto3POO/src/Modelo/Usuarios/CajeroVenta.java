/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

/**
 *
 * @author admin
 */
public class CajeroVenta {
    
    private String NombreCajero;
    private int CantidadProducto;
    
    public  CajeroVenta(String pNombre){
        CantidadProducto=0;
        NombreCajero=pNombre;
    }
    
    public String getNombreCajero(){
        return NombreCajero;
    }
    
    
    public int getCantidadVenta(){
        return CantidadProducto;
    }
    
    public void increaceAmount(){
        CantidadProducto++;
    }
}