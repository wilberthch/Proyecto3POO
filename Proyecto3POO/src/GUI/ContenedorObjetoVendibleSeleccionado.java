/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Modelo.Productos.ObjetoVendible;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author will
 */
public class ContenedorObjetoVendibleSeleccionado extends ContenedorObjetoVendible implements Comparable<ContenedorObjetoVendible>
{
    
    private static final MouseAdapter eventoClick = new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
                
                Component source = (Component)e.getSource();
                ContenedorObjetoVendibleSeleccionado panel = (ContenedorObjetoVendibleSeleccionado)source;
                panel.accionClick();
                
                
            }
        };

    public ContenedorObjetoVendibleSeleccionado(ObjetoVendible pObjectoVendible, iStalker pStalker) {
        super(pObjectoVendible, pStalker,true);
        
        addMouseListener(eventoClick);
        
    }
    
    @Override
    protected void accionClick()
    {
        
        alertar(true);

    }
    
    @Override
    public void alertar(boolean pRemover) {
        stalker.actualizar((iSujeto)this, pRemover);
    }

    @Override
    public int compareTo(ContenedorObjetoVendible o) {
        return objetoVendible.compareTo(o.getObjetoVendible());
    }
    
}
