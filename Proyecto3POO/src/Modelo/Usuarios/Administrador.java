/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

import Modelo.Productos.Combo;
import Modelo.Productos.Producto;
import Modelo.Productos.ProductoAgrandable;
import Modelo.Restaurante;
import Modelo.Facturacion.iFacturador;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author will
 */
public class Administrador extends Usuario 
{
    private Restaurante restaurante;
    
    public Administrador(Restaurante pRestaurante)
    {
        restaurante = pRestaurante;
    }
    
    public void guardarUsuario(Usuario pUsuario) throws NullPointerException
    {
        String nombreUsuario;
        String password;
        String nombre;
        LocalDate fechaNacimiento;
        String cedula;
        String telefono;
        char sexo;
        try
        {
            nombreUsuario = pUsuario.getNombreUsuario();
            password = pUsuario.getPassword();
            nombre = pUsuario.getNombre();
            fechaNacimiento = pUsuario.getFechaNacimiento();
            cedula = pUsuario.getCedula();
            telefono = pUsuario.getTelefono();
            sexo = pUsuario.getSexo();
        }
        catch(NullPointerException ex)
        {
            throw new NullPointerException("Parametro nulo");
        }
        
        try
        {
            
            Usuario usuario = restaurante.getUsuarioByUserName(nombreUsuario);
            usuario.setCedula(cedula);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setPassword(password);
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            usuario.setSexo(sexo);
            
            
            
        }
        catch(NullPointerException ex)
        {
            Usuario nuevoUsuario;
            
            if( pUsuario instanceof Administrador)
            {
                nuevoUsuario = new Administrador(restaurante);
                
            }
            else
            {
                nuevoUsuario = new Cajero((iFacturador) restaurante);
            }
            nuevoUsuario.setCedula(cedula);
            nuevoUsuario.setFechaNacimiento(fechaNacimiento);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setSexo(sexo);
            
            restaurante.agregarUsuario(pUsuario);
            
            
        }
        
    }
    
    public void removerUsuario(Usuario pUsuario)
    {
        restaurante.removerUsuario(pUsuario);
    }
    
    public void guardarProducto(Producto pProducto) throws NullPointerException
    {
        String nombre;
        double precio;
        double descuento;
        String rutaImagen;
        try
        {
            nombre = pProducto.getNombre();
            precio = pProducto.getPrecio();
            descuento = pProducto.getDescuento();
            rutaImagen = pProducto.getRutaImagen();
        }
        catch(NullPointerException ex)
        {
            throw new NullPointerException("Parametro nulo");
        }
        
        try
        {
            Producto producto = restaurante.getProductoPorNombre(nombre);
            producto.setPrecio(precio);
            producto.setDescuento(descuento);
            producto.setRutaImagen(rutaImagen);
        
        }
        catch(NullPointerException ex)
        {
            Producto nuevoProducto = null;
            if(pProducto instanceof ProductoAgrandable)
            {
                nuevoProducto = new ProductoAgrandable((ProductoAgrandable)pProducto);
                
            }
            else
            {
                nuevoProducto = new Producto(pProducto);
            }
            
            restaurante.agregarProducto(pProducto);
        }
        
        
    }
    
    public void removerProducto(Producto pProducto)
    {
        restaurante.removerProducto(pProducto);
    }
    
    public void guardarCombo(Combo pCombo) throws NullPointerException
    {
        double descuento;
        String nombre;
        LinkedList<Producto> productos;
        
        try
        {
            nombre = pCombo.getNombre();
            descuento = pCombo.getDescuento();
            productos = pCombo.getProductos();
        }
        catch(NullPointerException ex)
        {
            throw new NullPointerException("Parametro nulo");
        }
        
        try
        {
            Combo combo = restaurante.getComboPorNombre(nombre);
            combo.setDescuento(descuento);
            combo.setProductos(productos);
        }
        catch(NullPointerException ex)
        {
            Combo nuevoCombo = new Combo();
            nuevoCombo.setDescuento(descuento);
            nuevoCombo.setProductos(productos);
            restaurante.agregarCombo(pCombo);
            
            
        }
        
    }
    
    public void removerCombo(Combo pCombo)
    {
        restaurante.removerCombo(pCombo);
    }
    
    
    
}
