/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author will
 */
public final class ImageTools 
{
    public static BufferedImage resizeImage(BufferedImage pImagen, int width, int height) 
    {
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) imagen.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(pImagen, 0, 0, width, height, null);
        g2d.dispose();
        return imagen;
    }
    
    public static void cargarImagenALabel(JLabel pLabel, File pFile) throws IOException
    {
        BufferedImage imagen = ImageIO.read(pFile);
        BufferedImage imagenAjustada = resizeImage(imagen, pLabel.getWidth(), pLabel.getHeight());
        ImageIcon imagenIcon = new ImageIcon(imagenAjustada);
        pLabel.setIcon(imagenIcon);
    }
    
}
