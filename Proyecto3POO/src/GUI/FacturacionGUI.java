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
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JViewport;

/**
 *
 * @author will
 */
public class FacturacionGUI extends javax.swing.JFrame implements iStalker {
    
    private Cajero cajero;
    private LinkedList<Combo> combos;
    private LinkedList<Producto> productos;
    private LinkedList<ObjetoVendible> objectosSeleccionados;
    private LinkedList<ContenedorObjetoVendible> contenedoresSeleccionados;

    /**
     * Creates new form FacturacionGUI
     */
    public FacturacionGUI() {
        initComponents();
    }
    
    
    
    public FacturacionGUI(Cajero pCajero) {
        initComponents();
        cajero = pCajero;
        
        objectosSeleccionados = new LinkedList<>();
        contenedoresSeleccionados = new LinkedList<>();
        
        combos = cajero.getAllCombos();
        
        initProductosTab();
        initCombosTab();
 
    }
    
    private void refreshProductosTab()
    {
        JPanel view = ((JPanel)scp_Productos.getViewport().getView());
        Component[] contenedores = view.getComponents();
        for(Component componente : contenedores)
        {
            if(componente instanceof ContenedorObjetoVendible)
            {
                ContenedorObjetoVendible contenedor = (ContenedorObjetoVendible) componente;
                if(!objectosSeleccionados.contains(contenedor))
                {
                    contenedor.deseleccionar();
                    
                }
                
            }
            
        }
    }
    
    private void refreshCombosTab()
    {
        JPanel view = ((JPanel)scp_Combos.getViewport().getView());
        Component[] contenedores = view.getComponents();
        for(Component componente : contenedores)
        {
            if(componente instanceof ContenedorObjetoVendible)
            {
                ContenedorObjetoVendible contenedor = (ContenedorObjetoVendible) componente;
                if(!objectosSeleccionados.contains(contenedor))
                {
                    contenedor.deseleccionar();
                   
                }
                
            }
            
        }
    }
    
    private void initProductosTab()
    {
        productos = cajero.getAllProductos();
        
        
        
        GridBagLayout grid = new GridBagLayout();
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JPanel panel = new JPanel(grid);
        
        panel.setSize(this.getSize());
        
        scp_Productos.setViewportView(panel);
        JPanel view = ((JPanel)scp_Productos.getViewport().getView());
        
        int cuentaX = 1;
        int cuentaY = 0;
        
        for(Producto producto : productos)
        {
            ContenedorObjetoVendible contenedor = 
                    new ContenedorObjetoVendible((ObjetoVendible)producto, (iStalker)this);
            
         
            
            view.add(contenedor, constraints);
            constraints.gridx = cuentaX;
            constraints.gridy = cuentaY;
            
            cuentaX++;
            if(cuentaX == 4)
            {
                cuentaX = 0;
                cuentaY++;
            }
            
            
            
            
            
        }
        this.pack();
    }
    
    private void initCombosTab()
    {
        combos = cajero.getAllCombos();
        
        
        
        GridBagLayout grid = new GridBagLayout();
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JPanel panel = new JPanel(grid);
        
        panel.setSize(this.getSize());
        
        scp_Combos.setViewportView(panel);
        JPanel view = ((JPanel)scp_Combos.getViewport().getView());
        
        int cuentaX = 1;
        int cuentaY = 0;
        for(Combo combo : combos)
        {
            ContenedorObjetoVendible contenedor = 
                    new ContenedorObjetoVendible((ObjetoVendible)combo, (iStalker)this);
            
         
            
            view.add(contenedor, constraints);
            constraints.gridx = cuentaX;
            constraints.gridy = cuentaY;
            
            cuentaX++;
            if(cuentaX == 4)
            {
                cuentaX = 0;
                cuentaY++;
            }
            
            
            
            
            
        }
        this.pack();
    }
    
    private void initOrdenTab()
    {
        
        
        
        
        GridBagLayout grid = new GridBagLayout();
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JPanel panel = new JPanel(grid);
        
        panel.setSize(this.getSize());
        
        scp_Orden.setViewportView(panel);
        JPanel view = ((JPanel)scp_Orden.getViewport().getView());
        
        int cuentaX = 1;
        int cuentaY = 0;
        for(ObjetoVendible objetoVendible : objectosSeleccionados)
        {
            ContenedorObjetoVendibleSeleccionado contenedor = 
                    new ContenedorObjetoVendibleSeleccionado(objetoVendible, (iStalker)this);
            
            
            
            view.add(contenedor, constraints);
            constraints.gridx = cuentaX;
            constraints.gridy = cuentaY;
            
            cuentaX++;
            if(cuentaX == 4)
            {
                cuentaX = 0;
                cuentaY++;
            }
            
            
            
            
            
        }
        this.pack();
    }
    
    public void pagar(){
        double totall=0;
        for(ObjetoVendible objetoVendible : objectosSeleccionados){
                totall+=objetoVendible.getPrecio();}
        int impuesto = (int) (totall*0.1);
        totall+=impuesto;
        MontoTempo.setText(Double.toString(totall));
    }
    
    @Override
    public void actualizar(iSujeto pSujeto, boolean pRemover)
    {   
        
        ContenedorObjetoVendible contenedorObjetoVendible = (ContenedorObjetoVendible)pSujeto;
        if(pRemover)
        {
            objectosSeleccionados.remove(contenedorObjetoVendible.getObjetoVendible());
            contenedoresSeleccionados.remove(contenedorObjetoVendible);
            pagar();
            if(pSujeto instanceof ContenedorObjetoVendibleSeleccionado)
            {
                
                refreshProductosTab();
                refreshCombosTab();
                
            }
        }
        else
        {
            objectosSeleccionados.add(contenedorObjetoVendible.getObjetoVendible());
            contenedoresSeleccionados.add(contenedorObjetoVendible);
            pagar();
        }
        
        
        
        initOrdenTab();
        /*for(ObjetoVendible objetoVendible : objectosSeleccionados)
        {
            System.out.println(objetoVendible);
        }*/
    }
    
    private boolean esFormValido()
    {
        boolean res = true;
        
        res &= !objectosSeleccionados.isEmpty();
        
        return res;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        scp_Productos = new javax.swing.JScrollPane();
        scp_Combos = new javax.swing.JScrollPane();
        scp_Orden = new javax.swing.JScrollPane();
        btnFacturar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Monto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        MontoTempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturación");

        scp_Productos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scp_Productos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jTabbedPane1.addTab("Productos", scp_Productos);

        scp_Combos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scp_Combos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jTabbedPane1.addTab("Combos", scp_Combos);

        scp_Orden.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scp_Orden.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jTabbedPane1.addTab("Orden", scp_Orden);

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        jLabel1.setText("Efectivo Colones");

        jLabel2.setText("Total");

        MontoTempo.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MontoTempo)
                    .addComponent(Monto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(MontoTempo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean isDigit(){
        try{
            Double efectivo = Double.valueOf(Monto.getText());
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Monto en Efectivo no es valido");
                Monto.setText("");
                return false;
            }
        return true;
    }
    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        if(esFormValido())
        {
            if (isDigit()){
            
            Double efectivo = Double.valueOf(Monto.getText());
            LinkedList<ObjetoVendible> items = new LinkedList<>(objectosSeleccionados);
            Factura factura = new Factura(cajero, items);
            double total=0;
            for(ObjetoVendible objetoVendible : items){
                total+=objetoVendible.getPrecio();}
            if (efectivo-(total+(total*0.1))>=0){
            factura.setFacturaPagada();
            cajero.guardarFactura(factura);
            MontoTempo.setText("0");
            FacturaFinal facturaCreada = new FacturaFinal(factura,cajero.getNombre(),efectivo);
            facturaCreada.setVisible(true);
            
            
            objectosSeleccionados.clear();
            initProductosTab();
            initCombosTab();
            initOrdenTab();}else{
                JOptionPane.showMessageDialog(this, "Monto insuficiente");
            }}
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay items en la orden");
            MontoTempo.setText("0");
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

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
            java.util.logging.Logger.getLogger(FacturacionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturacionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturacionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturacionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturacionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Monto;
    private javax.swing.JLabel MontoTempo;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane scp_Combos;
    private javax.swing.JScrollPane scp_Orden;
    private javax.swing.JScrollPane scp_Productos;
    // End of variables declaration//GEN-END:variables
}
