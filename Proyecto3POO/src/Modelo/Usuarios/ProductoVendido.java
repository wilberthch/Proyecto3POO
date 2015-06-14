/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;


public class ProductoVendido {
    private String NombreProducto;
    private int CantidadProducto;
    
    public ProductoVendido(String pNombre){
        CantidadProducto=0;
        NombreProducto=pNombre;
    }
    
    public String getNombreProducto(){
        return NombreProducto;
    }
    
    
    public int getCantidadProducto(){
        return CantidadProducto;
    }
    
    public void increaceAmount(){
        CantidadProducto++;
    }
}
