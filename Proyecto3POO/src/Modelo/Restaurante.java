/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Facturacion.Factura;
import Modelo.Facturacion.iFacturador;
import Modelo.Productos.Combo;
import Modelo.Productos.Producto;
import Modelo.Usuarios.Administrador;
import Modelo.Usuarios.Cajero;
import Modelo.Usuarios.Usuario;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author will
 */
public class Restaurante implements iFacturador
{
    
    private final LinkedList<Usuario> usuarios;
    private final LinkedList<Producto> productos;
    private final LinkedList<Combo> combos;
    private final LinkedList<Factura> facturas;
    
    public Restaurante()
    {
        usuarios = new LinkedList<>();
        productos = new LinkedList<>();
        combos = new LinkedList<>();
        facturas = new LinkedList<>();
    }
    
    public Usuario getUsuarioByUserName(String pNombreUsuario) throws NullPointerException
    {
        Usuario usuario = null;
        
        for(Usuario user : usuarios)
        {
            if(pNombreUsuario.equals(user.getNombreUsuario()))
            {
                usuario = user;
                break;
            }
        }
        
        if(usuario == null)
        {
            throw new NullPointerException("Usuario no encontrado");
        }
        
        return usuario;
    }
    
    
    public LinkedList<Usuario> getAllUsuarios()
    {
        LinkedList<Usuario> tempUsuarios = new LinkedList<>(usuarios);
        
        Collections.sort(tempUsuarios);
        return tempUsuarios;
    }
    
    public void agregarUsuario(Usuario pUsuario)
    {
        usuarios.add(pUsuario);
    }
    
    public void removerUsuario(Usuario pUsuario)
    {
        usuarios.remove(pUsuario);
    }
    
    public LinkedList<Producto> getAllProductos()
    {
        LinkedList<Producto> tempProductos = new LinkedList<>(productos);
        
        Collections.sort(tempProductos);
        
        return tempProductos;
    }
    
    public Producto getProductoPorNombre(String pNombre) throws NullPointerException
    {
        Producto res = null;
        for(Producto producto : productos)
        {
            if(pNombre.equals(producto.getNombre()))
            {
                res = producto;
                break;
            }
        }
        
        if(res == null)
        {
            throw new NullPointerException("Producto no encontrado");
        }
        
        return res;
    }
    
    public void agregarProducto(Producto pProducto)
    {
        productos.add(pProducto);
    }
    
    public void removerProducto(Producto pProducto)
    {
        productos.remove(pProducto);
    }
    
    public LinkedList<Combo> getAllCombos()
    {
        LinkedList<Combo> tempCombos = new LinkedList<>(combos);
        Collections.sort(tempCombos);
        return tempCombos;
    }
    
    public Combo getComboPorNombre(String pNombre) throws NullPointerException
    {
        Combo res = null;
        for(Combo combo : combos)
        {
            if(pNombre.equals(combo.getNombre()))
            {
                res = combo;
                break;
            }
        }
        if(res == null)
        {
            throw new NullPointerException("Combo no encontrado");
        }
        return res;
    }
    
    public void agregarCombo(Combo pCombo)
    {
        combos.add(pCombo);
    }
    
    public void removerCombo(Combo pCombo)
    {
        combos.remove(pCombo);
    }
    
    public LinkedList<Factura> getAllFacturas()
    {
        return facturas;
    }
    
    @Override
    public Factura getFacturaPorId(int pId) throws NullPointerException {
        Factura res = null;
        
        for(Factura factura : facturas)
        {
            if(pId == factura.getNumFactura())
            {
                res = factura;
                break;
            }
        }
        if(res == null)
        {
            throw new NullPointerException("Factura no encontrada");
        }
        
        return res;
        
    }

    @Override
    public void agregarFactura(Factura pFactura) {
        facturas.add(pFactura);
    }

    @Override
    public void removerFactura(Factura pFactura) {
        facturas.remove(pFactura);
    }

    

    
    
    
    
    
    
    
}
