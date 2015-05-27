/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Facturacion.Factura;
import Modelo.Productos.Combo;
import Modelo.Productos.Producto;
import Modelo.Usuarios.Administrador;
import Modelo.Usuarios.Usuario;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author will
 */
public class Restaurante {
    
    private LinkedList<Usuario> usuarios;
    private LinkedList<Producto> productos;
    private LinkedList<Combo> combos;
    private LinkedList<Factura> facturas;
    
    public Restaurante()
    {
        usuarios = new LinkedList<>();
        productos = new LinkedList<>();
        combos = new LinkedList<>();
        facturas = new LinkedList<>();
        Administrador admin = new Administrador(this);
        admin.setCedula("fadad");
        admin.setNombreUsuario("Wiiiiiiiilberth");
        usuarios.add((Usuario)admin);
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
        Collections.sort(usuarios);
        return usuarios;
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
        Collections.sort(productos);
        
        return productos;
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
        
        Collections.sort(combos);
        return combos;
    }
    
    public void agregarCombo(Combo pCombo)
    {
        combos.add(pCombo);
    }
    
    public void removerCombo(Combo pCombo)
    {
        combos.remove(pCombo);
    }
    
    
    
    
    
    
}
