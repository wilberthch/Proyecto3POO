/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Modelo.Facturacion.Factura;
import Modelo.Productos.Combo;
import Modelo.Productos.ObjetoVendible;
import Modelo.Productos.Producto;
import Modelo.Usuarios.Cajero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;



public class FacturaFinal extends javax.swing.JFrame {

    private LinkedList<ObjetoVendible> items;
    private LocalDateTime now;
            
    public FacturaFinal() {
        initComponents();
    }
    
    public FacturaFinal(Factura pFactura,String pCajero,Double pEfectivo){
        initComponents();
        items =pFactura.getItems();
        now = pFactura.getFecha();
        String Todo = "";
        double monto=0;
        Todo+="Fecha de Factura "+now+"\n";
        Todo+="Numero de la Factura "+ pFactura.getNumFactura()+"\n"+"Cajero: "+pCajero+"\n"+"\n";
        Todo+="Producto                     Precio"+"\n"+"---------------------------------------------------"+"\n";
        
        for(ObjetoVendible objetoVendible : items)
        {
            int finale=Todo.length();
            finale+=20;
            Todo+=objetoVendible.getNombre()+"                         ";
            monto +=objetoVendible.getPrecio();

            Todo+=objetoVendible.getPrecio()+"\n";
        }
        Todo+="---------------------------------------------------"+"\n";
        Todo+="Subtotal                       "+monto+"\n";
        int o=(int) (monto*0.1);
        Todo+="10%                              "+o+"\n";
        monto+=o;
        Todo+="Total                             "  +monto+"\n"+"\n";
        Todo+="                                   Entregado         Cambio"+"\n";
        Todo+="Efectivo Colones        "+pEfectivo+"              "+(pEfectivo-monto)+"\n"+"\n"+"\n";
        Todo+="                Impuestos incluidos"+"\n";
        Todo+="      *** GRACIAS POR SU VISITA ***";
        Impresion.setText(Todo.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Imprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Impresion = new javax.swing.JTextArea();

        jLabel1.setText("Factura Creada!");

        Imprimir.setText("Imprimir");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });

        Impresion.setEditable(false);
        Impresion.setColumns(20);
        Impresion.setRows(5);
        jScrollPane1.setViewportView(Impresion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Imprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Imprimir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
        JOptionPane.showMessageDialog(this, "Se Imprimio exitosamente!");
    }//GEN-LAST:event_ImprimirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacturaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Impresion;
    private javax.swing.JButton Imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
