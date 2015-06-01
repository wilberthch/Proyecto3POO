/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Productos;

/**
 *
 * @author will
 */
public class ProductoAgrandable extends Producto implements iAgrandable {
    
    //cantidad de veces que se ha agrandado el producto
    private int contadorAgrandados;
    //cantidad máxima en la que se puede agrandar un producto
    private int MaxAgrandados;
    //el porcentaje que se va a restar o aumentar del producto
    private static final double porcentajePrecioAgrandado = 10;
    //la cantidad que se resta o aumenta al precio del producto cuando se agranda o reduce
    private double cantidadOperable;
    
    public ProductoAgrandable(ProductoAgrandable pProducto)
    {
        super((Producto)pProducto);
        MaxAgrandados = pProducto.getMaxAgrandados();
        contadorAgrandados = 0;
        cantidadOperable = precio * porcentajePrecioAgrandado / 100;
    }
    
    public ProductoAgrandable(int pMaxAgrandados)
    {
        MaxAgrandados = pMaxAgrandados;
        contadorAgrandados = 0;
        cantidadOperable = precio * porcentajePrecioAgrandado / 100;
    }

    @Override
    public void agrandar() throws Exception {
        if(contadorAgrandados == MaxAgrandados)
        {
            throw new Exception("Máximo de agrandamientos alcanzado");
        }
        
        precio += cantidadOperable;
    }

    @Override
    public void reducir() throws Exception {
        if(contadorAgrandados == 0)
        {
            throw new Exception("cantidad minima de reducciones alcanzada");
        }
        
        precio -= cantidadOperable;
    }
    
    /**
     * @return the MaxAgrandados
     */
    public int getMaxAgrandados() {
        return MaxAgrandados;
    }

    /**
     * @param MaxAgrandados the MaxAgrandados to set
     */
    public void setMaxAgrandados(int MaxAgrandados) {
        this.MaxAgrandados = MaxAgrandados;
    }
    
}

    
