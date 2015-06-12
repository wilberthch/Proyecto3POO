/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Tools.ImageTools;
import Modelo.Productos.ObjetoVendible;
import Modelo.Productos.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author will
 */
public class ContenedorObjetoVendible extends JPanel implements iSujeto
{
    protected ObjetoVendible objetoVendible;
    protected iStalker stalker;
    private JLabel lbl_Precio;
    private JLabel lbl_ImagenProducto;
    private JLabel lbl_Descuento;
    private DropShadowBorder border;
    private static  Color DEFAULT_COLOR;
    private static final Color SELECTED_COLOR = Color.YELLOW;
    public static final Dimension DEFAULT_SIZE = new Dimension(140,180);
    
    
    private boolean esSeleccionado;
    
    private static final MouseAdapter eventoClick = new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
                
                Component source = (Component)e.getSource();
                ContenedorObjetoVendible panel = (ContenedorObjetoVendible)source;
                panel.accionClick();
                
                
            }
        };
    
    
    public ContenedorObjetoVendible(ObjetoVendible pObjectoVendible, iStalker pStalker)
    {
        
        DEFAULT_COLOR = this.getBackground();
        esSeleccionado = false;
        
        objetoVendible = pObjectoVendible;
        stalker = pStalker;
        
        border = new DropShadowBorder();
        border.setShadowColor(Color.BLACK);
        border.setShowLeftShadow(true);
        border.setShowLeftShadow(true);
        border.setShowLeftShadow(true);
        border.setShowRightShadow(true);
        border.setShowBottomShadow(true);
        border.setShowTopShadow(true);
        
        setSize(DEFAULT_SIZE);
        
        
        String nombreProducto = pObjectoVendible.getNombre();
        String precioProducto = Double.toString(pObjectoVendible.getPrecio());
        String descuentoProducto = Double.toString(pObjectoVendible.getDescuento());
        File imagenProducto = new File(pObjectoVendible.getRutaImagen());
        
        setOpaque(true);
        setBorder(BorderFactory.createTitledBorder(border,nombreProducto));
        lbl_Precio = new JLabel(precioProducto, SwingConstants.CENTER);
        lbl_Precio.setSize(100, 20);
        lbl_Descuento = new JLabel(descuentoProducto, SwingConstants.CENTER);
        lbl_Descuento.setSize(100, 20);
        lbl_ImagenProducto = new JLabel("", SwingConstants.CENTER);
        lbl_ImagenProducto.setSize(145, 130);
        
        
        try{
            ImageTools.cargarImagenALabel(lbl_ImagenProducto, imagenProducto);
        }catch (Exception x){}
        
        
        
        
        setLayout(new BorderLayout());
        
        add(lbl_Descuento, BorderLayout.NORTH);
        add(lbl_ImagenProducto, BorderLayout.CENTER);
        add(lbl_Precio, BorderLayout.SOUTH);
        
        
        addMouseListener(eventoClick);
        
    }
    
    protected ContenedorObjetoVendible(ObjetoVendible pObjectoVendible, iStalker pStalker, boolean esObjetoSeleccinado)
    {
        
        DEFAULT_COLOR = this.getBackground();
        esSeleccionado = false;
        
        objetoVendible = pObjectoVendible;
        stalker = pStalker;
        
        border = new DropShadowBorder();
        border.setShadowColor(Color.BLACK);
        border.setShowLeftShadow(true);
        border.setShowLeftShadow(true);
        border.setShowLeftShadow(true);
        border.setShowRightShadow(true);
        border.setShowBottomShadow(true);
        border.setShowTopShadow(true);
        
        setSize(DEFAULT_SIZE);
        
        
        String nombreProducto = pObjectoVendible.getNombre();
        String precioProducto = Double.toString(pObjectoVendible.getPrecio());
        String descuentoProducto = Double.toString(pObjectoVendible.getDescuento());
        File imagenProducto = new File(pObjectoVendible.getRutaImagen());
        
        setOpaque(true);
        setBorder(BorderFactory.createTitledBorder(border,nombreProducto));
        lbl_Precio = new JLabel(precioProducto, SwingConstants.CENTER);
        lbl_Precio.setSize(100, 20);
        lbl_Descuento = new JLabel(descuentoProducto, SwingConstants.CENTER);
        lbl_Descuento.setSize(100, 20);
        lbl_ImagenProducto = new JLabel("", SwingConstants.CENTER);
        lbl_ImagenProducto.setSize(145, 130);
        
        
        try{
            ImageTools.cargarImagenALabel(lbl_ImagenProducto, imagenProducto);
        }catch (Exception x){}
        
        
        
        
        setLayout(new BorderLayout());
        
        add(lbl_Descuento, BorderLayout.NORTH);
        add(lbl_ImagenProducto, BorderLayout.CENTER);
        add(lbl_Precio, BorderLayout.SOUTH);
        
        if(!esObjetoSeleccinado)
        {
            addMouseListener(eventoClick);
        }
        
        
    }
    
    public boolean esSeleccionado()
    {
        return esSeleccionado;
    }
    
    public void deseleccionar()
    {
        esSeleccionado = false;
        this.setBackground(DEFAULT_COLOR);
        
    }
    
    public ObjetoVendible getObjetoVendible()
    {
        return objetoVendible;
    }
    
    protected void accionClick()
    {
        
        
        if(esSeleccionado)
        {
            this.setBackground(DEFAULT_COLOR);
        }
        else
        {
            this.setBackground(SELECTED_COLOR);
        }
        alertar(esSeleccionado);
        esSeleccionado = !esSeleccionado;

    }
    
    @Override
    public void alertar(boolean pRemover) {
        stalker.actualizar((iSujeto)this, pRemover);
    }
}
