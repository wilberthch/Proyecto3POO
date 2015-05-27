/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

import Modelo.Restaurante;
import java.time.LocalDate;

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
                nuevoUsuario = new Cajero();
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
    
}
