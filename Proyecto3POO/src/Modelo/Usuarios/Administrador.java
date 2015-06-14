/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

import Modelo.Facturacion.Factura;
import Modelo.Productos.Combo;
import Modelo.Productos.Producto;
import Modelo.Productos.ProductoAgrandable;
import Modelo.Restaurante;
import Modelo.Facturacion.iFacturador;
import Modelo.Productos.ObjetoVendible;
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

    public Administrador()
    {

    }

    public LinkedList<Usuario> getAllUsuarios()
    {
        return restaurante.getAllUsuarios();
    }

    public Usuario getUsuarioByUserName(String pNombreUsuario)
    {
        return restaurante.getUsuarioByUserName(pNombreUsuario);
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
                System.out.println("hoooolissss");
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

    public void removerUsuario(String pNombreUsuario)
    {
        Usuario usuario = restaurante.getUsuarioByUserName(pNombreUsuario);

        restaurante.removerUsuario(usuario);
    }


    public LinkedList<Producto> getAllProductos()
    {
        return restaurante.getAllProductos();
    }
    
    public LinkedList<Factura> getAllFacturas()
    {
        return restaurante.getAllFacturas();
    }

    public Producto getProductoPorNombre(String pNombreProducto) throws NullPointerException
    {
        return restaurante.getProductoPorNombre(pNombreProducto);
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

    public void removerProducto(String pNombreProducto)
    {
        Producto producto = getProductoPorNombre(pNombreProducto);
        restaurante.removerProducto(producto);
    }


    public LinkedList<Combo> getAllCombos()
    {
        return restaurante.getAllCombos();
    }
    
    public boolean isWithin(LinkedList<ProductoVendido> pReporte,String pNombre){
        for (int u=0;u<pReporte.size();u++){
            if (pReporte.get(u).getNombreProducto().equals(pNombre))
                return true;
        }
        return false;
    }
    public boolean isWithin2(LinkedList<CajeroVenta> pReporte,String pNombre){
        for (int u=0;u<pReporte.size();u++){
            if (pReporte.get(u).getNombreCajero().equals(pNombre))
                return true;
        }
        return false;
    }
    
    public LinkedList<ProductoVendido> getReporteProductos()
    {
        LinkedList<ProductoVendido> reporte = new LinkedList<>();
        LinkedList<Factura> facturas = restaurante.getAllFacturas();
        for(int i = 0;i<facturas.size();i++){
            LinkedList<ObjetoVendible> items = facturas.get(i).getItems();
            for(int p=0;p<items.size();p++){
                ObjetoVendible Productotemp=items.get(p);
                String nombre = Productotemp.getNombre();
                if(isWithin(reporte,nombre))
                    break;
                ProductoVendido Reportar = new ProductoVendido(nombre);
                reporte.add(Reportar);
            }
        }
        
        for(int i = 0;i<facturas.size();i++){
            LinkedList<ObjetoVendible> items = facturas.get(i).getItems();
            for(int p=0;p<items.size();p++){
                ObjetoVendible Productotemp=items.get(p);
                String nombre = Productotemp.getNombre();
                for (int u=0;u<reporte.size();u++){
                    if (reporte.get(u).getNombreProducto().equals(nombre))
                        reporte.get(u).increaceAmount();
                }
            }
        }
        return reporte;
    }
    
    public LinkedList<CajeroVenta> getReporteCajero()
    {
        LinkedList<CajeroVenta> reporte = new LinkedList<>();
        LinkedList<Factura> facturas = restaurante.getAllFacturas();
        for (int y=0;y<facturas.size();y++){
            System.out.println(facturas.get(y).getCajero().getNombre());
        }
        for(int i = 0;i<facturas.size();i++){
            Cajero caj = facturas.get(i).getCajero();
            String nombre = caj.getNombre();
            if(isWithin2(reporte,nombre))
                continue;
            CajeroVenta Reportar = new CajeroVenta(nombre);
            reporte.add(Reportar);
        }
        
        for(int i = 0;i<facturas.size();i++){
            Cajero caj = facturas.get(i).getCajero();
            String nombre = caj.getNombre();
            for(int o=0;o<reporte.size();o++){
                if (reporte.get(o).getNombreCajero()==nombre){
                    reporte.get(o).increaceAmount();
                    break;
                }
            }
        }
        return reporte;
    }

    public Combo getComboPorNombre(String pNombreCombo) throws NullPointerException
    {
        return restaurante.getComboPorNombre(pNombreCombo);
    }

    public void guardarCombo(Combo pCombo) throws NullPointerException
    {
        double descuento;
        String nombre;
        String rutaImagen;
        LinkedList<Producto> productos;

        try
        {
            nombre = pCombo.getNombre();
            descuento = pCombo.getDescuento();
            productos = pCombo.getProductos();
            rutaImagen = pCombo.getRutaImagen();
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
            combo.setRutaImagen(rutaImagen);
        }
        catch(NullPointerException ex)
        {
            Combo nuevoCombo = new Combo();
            nuevoCombo.setDescuento(descuento);
            nuevoCombo.setProductos(productos);
            nuevoCombo.setRutaImagen(rutaImagen);
            restaurante.agregarCombo(pCombo);


        }

    }
    
    public void removerCombo(String pNombreCombo)
    {
        Combo combo = getComboPorNombre(pNombreCombo);
        restaurante.removerCombo(combo);
    }

    /**
     * @param restaurante the restaurante to set
     */
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public void actualizarRestaurante(Restaurante pRestaurante) {
        restaurante = pRestaurante;
    }



}
