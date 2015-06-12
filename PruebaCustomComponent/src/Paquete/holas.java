/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author will
 */
public class holas extends JPanel{
    
    private JButton boton;
    private JLabel nombre;
    private JLabel precio;
    
        
    private static final MouseAdapter eventoClick = new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null, "hola");
            }
        };
    
    private static final Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK);
    
    public holas()
    {
        
        setOpaque(true);
        setBorder(BorderFactory.createTitledBorder(border,"Hola"));
        boton = new JButton("?");
        nombre = new JLabel();
        precio = new JLabel();
        setPreferredSize( new Dimension( 140, 180 ) );
        nombre.setHorizontalAlignment(JLabel.CENTER);
        nombre.setText("hola");
        //nombre.setBorder(border);
        precio.setText("1000000");
        precio.setHorizontalAlignment(JLabel.CENTER);
        //precio.setBorder(border);
        
        
        setLayout(new BorderLayout());
        
        
        
        add(boton, BorderLayout.CENTER);
        //add(nombre, BorderLayout.NORTH);
        add(nombre, BorderLayout.SOUTH);
//        layout.putConstraint(SpringLayout.WEST, boton, 5, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, boton, 5, SpringLayout.NORTH, panel);
//        
//        layout.putConstraint(SpringLayout.WEST, precio, 5, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.SOUTH, boton);
        
        precio.addMouseListener(eventoClick);
        boton.addMouseListener(eventoClick);
        addMouseListener(eventoClick);
        
        
        
        
    }
    
    public void setNombre(String pNombre)
    {
        nombre.setText(pNombre);
    }
    
    public void setPrecio(String pPrecio)
    {
        precio.setText(pPrecio);
    }
    
}
