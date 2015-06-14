/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Tools.FileTools;
import GUI.Tools.NonEditableTableModel;
import GUI.Tools.ImageTools;
import Modelo.Productos.ObjetoVendible;
import Modelo.Productos.Combo;
import Modelo.Productos.Producto;
import Modelo.Productos.ProductoAgrandable;
import Modelo.Restaurante;
import Modelo.Usuarios.Administrador;
import Modelo.Usuarios.Cajero;
import Modelo.Usuarios.CajeroVenta;
import Modelo.Usuarios.ProductoVendido;
import Modelo.Usuarios.Usuario;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author will
 */
public class AdministracionRestaurante extends javax.swing.JFrame {


    private Administrador administrador;

    private static final NonEditableTableModel usuariosTableModel = new NonEditableTableModel();
    private static final String[] usuariosHeaders = {"Usuario"};
    private static LinkedList<Usuario> usuarios;


    private static final NonEditableTableModel productosTableModel = new NonEditableTableModel();
    private static final String[] productosHeaders = {"Producto"};
    private static LinkedList<Producto> productos;
    private String rutaImagenProducto = ObjetoVendible.IMAGEN_DEFAULT;
    private String rutaImagenCombo = ObjetoVendible.IMAGEN_DEFAULT;
    
    private static final NonEditableTableModel combosTableModel = new NonEditableTableModel();
    private static final String[] combosHeaders = {"Combo"};
    private static LinkedList<Combo> combos;
    
    private static final NonEditableTableModel productosComboTableModel = new NonEditableTableModel();
    private static final String[] productosComboHeaders = {"Producto", "Precio", "Cantidad para el Combo"};
    private Combo comboActual;
    
    private static final NonEditableTableModel pReporteTableModel = new NonEditableTableModel();
    private static final String[] pReporteHeaders = {"Productos", "Cantidad"};
    
    private static final NonEditableTableModel pReporteCTableModel = new NonEditableTableModel();
    private static final String[] pReporteCHeaders = {"Cajero", "Cantidad de Ventas"};

    private static final DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG,PNG, GIF Images", "jpg","png", "gif");



    /**
     * Creates new form AdministracionRestaurante
     */
    public AdministracionRestaurante() {
        initComponents();
        usuarios = new LinkedList<>();
        comboActual = new Combo();
        refreshTblUsuarios();
        refreshTblProductos();
        refreshTblCombos();
        refreshTblProductosCombo();
        boolean chekeado = ckb_ProductoAgrandable.isSelected();
        pnl_ContenedorProductoAgrandable.setVisible(chekeado);
        
    }

     /**
     * Creates new form AdministracionRestaurante
     * @param pAdministrador
     */
    public AdministracionRestaurante(Administrador pAdministrador) {
        try {
            initComponents();
            boolean chekeado = ckb_ProductoAgrandable.isSelected();
            pnl_ContenedorProductoAgrandable.setVisible(chekeado);
            administrador = pAdministrador;
            comboActual = new Combo();
            refreshTblUsuarios();
            refreshTblProductos();
            refreshTblCombos();
            refreshTblProductosCombo();
            File file = new File(ObjetoVendible.IMAGEN_DEFAULT);
            ImageTools.cargarImagenALabel(lbl_ProductoImagen, file);
            ImageTools.cargarImagenALabel(lbl_ComboImagen, file);

        } catch (IOException ex) {
            Logger.getLogger(AdministracionRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   
    
    private void refreshTblUsuarios()
    {
        usuarios = administrador.getAllUsuarios();
        int listSize = usuarios.size();
        String[][] usuariosDatos = new String[listSize][1];
        for(int index = 0; index < listSize; index++)
        {
            Usuario usuario = usuarios.get(index);
            usuariosDatos[index][0]= usuario.getNombreUsuario();

        }

        usuariosTableModel.setDataVector(usuariosDatos, usuariosHeaders);
        tbl_Usuarios.setModel(usuariosTableModel);

    }

    private void refreshTblProductos()
    {
        productos = administrador.getAllProductos();
        int listSize = productos.size();
        String[][] productosDatos = new String[listSize][1];
        for(int index = 0; index < listSize; index++)
        {
            Producto producto = productos.get(index);
            productosDatos[index][0]= producto.getNombre();

        }

        productosTableModel.setDataVector(productosDatos, productosHeaders);
        tbl_Productos.setModel(productosTableModel);

    }
    
    private void refreshTblCombos()
    {
        combos = administrador.getAllCombos();
        int listSize = combos.size();
        String[][] combosDatos = new String[listSize][1];
        for(int index = 0; index < listSize; index++)
        {
            Combo combo = combos.get(index);
            combosDatos[index][0]= combo.getNombre();
            
        }
        
        combosTableModel.setDataVector(combosDatos, combosHeaders);
        tbl_Combos.setModel(combosTableModel);
        
    }
    
    private void refreshTblReporte(LinkedList<ProductoVendido> pReporte)
    {
         
        String[][] reporteDatos = new String[pReporte.size()][2];
        for(int index = 0; index < pReporte.size(); index++)
        {
            
            reporteDatos[index][0]= pReporte.get(index).getNombreProducto();
            reporteDatos[index][1] = String.valueOf(pReporte.get(index).getCantidadProducto());
        }
        
        pReporteTableModel.setDataVector(reporteDatos, pReporteHeaders);
        reporte.setModel(pReporteTableModel);

    }
    
    private void refreshTblReporteVenta(LinkedList<CajeroVenta> pReporte)
    {
        String[][] reporteDatos = new String[pReporte.size()][2];
        for(int index = 0; index < pReporte.size(); index++)
        {
            
            reporteDatos[index][0]= pReporte.get(index).getNombreCajero();
            reporteDatos[index][1] = String.valueOf(pReporte.get(index).getCantidadVenta());
        }
        
        pReporteCTableModel.setDataVector(reporteDatos, pReporteCHeaders);
        reporteCajero.setModel(pReporteCTableModel);

    }
    
    private void refreshTblProductosCombo()
    {
        LinkedList<Producto> productosCombo = comboActual.getProductos();
        int listSize = productosCombo.size();
        String[][] productosComboDatos = new String[listSize][3];
        for(int index = 0; index < listSize; index++)
        {
            Producto producto = productosCombo.get(index);
            productosComboDatos[index][0] = producto.getNombre();
            productosComboDatos[index][1] = Double.toString(producto.getPrecio());
            productosComboDatos[index][2] = "1";
            
        }
        
        productosComboTableModel.setDataVector(productosComboDatos, productosComboHeaders);
        tbl_ProductosCombo.setModel(productosComboTableModel);
        
        String precioTotalCombo = Double.toString(comboActual.getPrecio());
        lbl_PrecioTotalCombo.setText(precioTotalCombo);
        
    }
    
    

    private boolean esFormUsuariosValido()
    {
        boolean res = true;

        res &= !tf_NombreUsuario.getText().isEmpty();
        res &= !tf_Telefono.getText().isEmpty();
        res &= !tf_Cedula.getText().isEmpty();
        res &= !tf_FechaNacimiento.getText().isEmpty();
        res &= !tf_UserNameUsuario.getText().isEmpty();
        String password = new String(tf_Password.getPassword());
        res &= !password.isEmpty();

        return res;
    }

    private boolean esFormProductosValido()
    {
        boolean res = true;

        res &= !tf_NombreProducto.getText().isEmpty();
        res &= !tf_PrecioProducto.getText().isEmpty();
        res &= !tf_DescuentoProducto.getText().isEmpty();

        return res;
    }
    
    private boolean esFormCombosValido()
    {
        boolean res = true;
        
        res &= !tf_NombreCombo.getText().isEmpty();
        res &= !tf_DescuentoCombo.getText().isEmpty();
        res &= !comboActual.getProductos().isEmpty();
        
        return res;
    }
    

    private void limpiarFormUsuarios()
    {
        tf_NombreUsuario.setText("");
        tf_Cedula.setText("");
        tf_Telefono.setText("");
        cbx_Sexo.setSelectedIndex(0);
        tf_FechaNacimiento.setText("");
        tf_UserNameUsuario.setText("");
        tf_Password.setText("");
        rb_Administrador.setSelected(true);
        rb_Cajero.setSelected(false);
    }
    
    private void limpiarFormCombos()
    {
        tf_NombreCombo.setText("");
        tf_DescuentoCombo.setText("0");
        comboActual = new Combo();
        File file = new File(ObjetoVendible.IMAGEN_DEFAULT);
        try{
        ImageTools.cargarImagenALabel(lbl_ComboImagen, file);
        }catch (Exception x){

        }
        refreshTblProductosCombo();
    }


    private void limpiarFormProducto()
    {
        tf_NombreProducto.setText("");
        tf_PrecioProducto.setText("");
        tf_DescuentoProducto.setText("0");
        ckb_ProductoAgrandable.setSelected(false);
        pnl_ContenedorProductoAgrandable.setVisible(false);
        spn_MaximoAgrandamiento.setValue((Object)1);
        File file = new File(ObjetoVendible.IMAGEN_DEFAULT);
        try{
        ImageTools.cargarImagenALabel(lbl_ProductoImagen, file);
        }catch (Exception x){

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgr_Usuarios = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Productos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_NombreProducto = new javax.swing.JTextField();
        tf_PrecioProducto = new javax.swing.JFormattedTextField();
        tf_DescuentoProducto = new javax.swing.JFormattedTextField();
        lbl_ProductoImagen = new javax.swing.JLabel();
        btn_CargarImagenProducto = new javax.swing.JButton();
        btn_GuardarProducto = new javax.swing.JButton();
        btn_BorrarProducto = new javax.swing.JButton();
        btn_CancelarProducto = new javax.swing.JButton();
        btn_SalirProducto = new javax.swing.JButton();
        ckb_ProductoAgrandable = new javax.swing.JCheckBox();
        pnl_ContenedorProductoAgrandable = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        spn_MaximoAgrandamiento = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Combos = new javax.swing.JTable();
        btn_ModificarProductos = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ProductosCombo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_NombreCombo = new javax.swing.JTextField();
        tf_DescuentoCombo = new javax.swing.JFormattedTextField();
        btn_GuardarCombo = new javax.swing.JButton();
        btn_BorrarCombo = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lbl_PrecioTotalCombo = new javax.swing.JLabel();
        lbl_ComboImagen = new javax.swing.JLabel();
        btn_CargarImagenCombo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        tf_NombreUsuario = new javax.swing.JTextField();
        tf_Cedula = new javax.swing.JTextField();
        tf_Telefono = new javax.swing.JFormattedTextField();
        cbx_Sexo = new javax.swing.JComboBox();
        tf_FechaNacimiento = new javax.swing.JFormattedTextField();
        tf_UserNameUsuario = new javax.swing.JTextField();
        tf_Password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_GuardarUsuario = new javax.swing.JButton();
        btn_CancelarUsuario = new javax.swing.JButton();
        btn_SalirUsuario = new javax.swing.JButton();
        rb_Administrador = new javax.swing.JRadioButton();
        rb_Cajero = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        btn_Borrar = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        reporte = new javax.swing.JTable();
        VerReporte = new javax.swing.JButton();
        salirReporte = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        reporteCajero = new javax.swing.JTable();
        VerReporteCajero = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AdRes [Administración]");

        tbl_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Productos);

        jLabel9.setText("Nombre");

        jLabel10.setText("Precio");

        jLabel11.setText("Descuento");

        tf_PrecioProducto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));

        tf_DescuentoProducto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));
        tf_DescuentoProducto.setText("0");

        lbl_ProductoImagen.setBackground(new java.awt.Color(1, 1, 1));

        btn_CargarImagenProducto.setText("Cargar Imagen");
        btn_CargarImagenProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CargarImagenProductoActionPerformed(evt);
            }
        });

        btn_GuardarProducto.setText("Guardar");
        btn_GuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarProductoActionPerformed(evt);
            }
        });

        btn_BorrarProducto.setText("Borrar");
        btn_BorrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrarProductoActionPerformed(evt);
            }
        });

        btn_CancelarProducto.setText("Cancelar");
        btn_CancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarProductoActionPerformed(evt);
            }
        });

        btn_SalirProducto.setText("Salir");
        btn_SalirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirProductoActionPerformed(evt);
            }
        });

        ckb_ProductoAgrandable.setText("Producto Agrandable");
        ckb_ProductoAgrandable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_ProductoAgrandableActionPerformed(evt);
            }
        });

        jLabel12.setText("Cantidad Agrandamientos");

        spn_MaximoAgrandamiento.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        javax.swing.GroupLayout pnl_ContenedorProductoAgrandableLayout = new javax.swing.GroupLayout(pnl_ContenedorProductoAgrandable);
        pnl_ContenedorProductoAgrandable.setLayout(pnl_ContenedorProductoAgrandableLayout);
        pnl_ContenedorProductoAgrandableLayout.setHorizontalGroup(
            pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ContenedorProductoAgrandableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(spn_MaximoAgrandamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        pnl_ContenedorProductoAgrandableLayout.setVerticalGroup(
            pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContenedorProductoAgrandableLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(spn_MaximoAgrandamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_ProductoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btn_CargarImagenProducto)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ckb_ProductoAgrandable)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGap(53, 53, 53)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tf_PrecioProducto)
                                .addComponent(tf_NombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                .addComponent(tf_DescuentoProducto, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addComponent(pnl_ContenedorProductoAgrandable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_GuardarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_BorrarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_CancelarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_SalirProducto)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_PrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_DescuentoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ckb_ProductoAgrandable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_ContenedorProductoAgrandable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ProductoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btn_CargarImagenProducto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_GuardarProducto)
                    .addComponent(btn_BorrarProducto)
                    .addComponent(btn_CancelarProducto)
                    .addComponent(btn_SalirProducto))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        tbl_Combos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Combos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CombosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_Combos);

        btn_ModificarProductos.setText("Modificar Productos");
        btn_ModificarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarProductosActionPerformed(evt);
            }
        });

        tbl_ProductosCombo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbl_ProductosCombo);

        jLabel13.setText("Productos del Combo");

        jLabel14.setText("Nombre");

        jLabel15.setText("Descuento");

        tf_DescuentoCombo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));
        tf_DescuentoCombo.setText("0");

        btn_GuardarCombo.setText("Guardar");
        btn_GuardarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarComboActionPerformed(evt);
            }
        });

        btn_BorrarCombo.setText("Borrar");
        btn_BorrarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrarComboActionPerformed(evt);
            }
        });

        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_salir.setText("Salir");
        btn_salir.setToolTipText("");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jLabel16.setText("Precio total");

        lbl_PrecioTotalCombo.setText("O");

        lbl_ComboImagen.setBackground(new java.awt.Color(1, 1, 1));

        btn_CargarImagenCombo.setText("Cargar Imagen");
        btn_CargarImagenCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CargarImagenComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_NombreCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                            .addComponent(tf_DescuentoCombo)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btn_ModificarProductos)
                                        .addGap(111, 111, 111)
                                        .addComponent(jLabel16))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbl_ComboImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(btn_CargarImagenCombo)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(lbl_PrecioTotalCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_GuardarCombo)
                        .addGap(18, 18, 18)
                        .addComponent(btn_BorrarCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ModificarProductos)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(lbl_PrecioTotalCombo)))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tf_NombreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tf_DescuentoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ComboImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btn_CargarImagenCombo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_GuardarCombo)
                    .addComponent(btn_BorrarCombo)
                    .addComponent(btn_Cancelar)
                    .addComponent(btn_salir))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Combos", jPanel3);

        tbl_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Usuarios);

        try {
            tf_Telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cbx_Sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        tf_FechaNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        tf_FechaNacimiento.setToolTipText("DD/MM/YYYY");

        jLabel1.setText("Nombre");
        jLabel1.setToolTipText("");

        jLabel2.setText("Telefono");
        jLabel2.setToolTipText("");

        jLabel3.setText("Cedula");
        jLabel3.setToolTipText("");

        jLabel4.setText("Sexo");
        jLabel4.setToolTipText("");

        jLabel5.setText("FechaNacimiento");
        jLabel5.setToolTipText("");

        jLabel6.setText("Nombre de Usuario");
        jLabel6.setToolTipText("");

        jLabel7.setText("Contraseña");
        jLabel7.setToolTipText("");

        btn_GuardarUsuario.setText("Guardar");
        btn_GuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarUsuarioActionPerformed(evt);
            }
        });

        btn_CancelarUsuario.setText("Cancelar");
        btn_CancelarUsuario.setToolTipText("");
        btn_CancelarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarUsuarioActionPerformed(evt);
            }
        });

        btn_SalirUsuario.setText("Salir");
        btn_SalirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirUsuarioActionPerformed(evt);
            }
        });

        bgr_Usuarios.add(rb_Administrador);
        rb_Administrador.setSelected(true);
        rb_Administrador.setText("Administrador");

        bgr_Usuarios.add(rb_Cajero);
        rb_Cajero.setText("Cajero");

        jLabel8.setText("Tipo de Usuario");

        btn_Borrar.setText("Borrar");
        btn_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_GuardarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Borrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_CancelarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_SalirUsuario)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_Administrador)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tf_FechaNacimiento)
                                .addComponent(tf_Cedula, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tf_NombreUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tf_Telefono)
                                .addComponent(cbx_Sexo, 0, 145, Short.MAX_VALUE)
                                .addComponent(tf_UserNameUsuario)
                                .addComponent(tf_Password))
                            .addComponent(rb_Cajero))
                        .addGap(85, 85, 85))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_NombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_FechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_UserNameUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rb_Administrador)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_Cajero)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CancelarUsuario)
                    .addComponent(btn_GuardarUsuario)
                    .addComponent(btn_SalirUsuario)
                    .addComponent(btn_Borrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", jPanel1);

        reporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Productos", "Cantidad"
            }
        ));
        jScrollPane5.setViewportView(reporte);

        VerReporte.setText("Ver reporte");
        VerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerReporteActionPerformed(evt);
            }
        });

        salirReporte.setText("Salir");
        salirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VerReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salirReporte)
                .addGap(56, 56, 56))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VerReporte)
                    .addComponent(salirReporte))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Productos", jPanel5);

        reporteCajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cajero", "Cantidad de Ventas"
            }
        ));
        jScrollPane6.setViewportView(reporteCajero);

        VerReporteCajero.setText("Ver reporte");
        VerReporteCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerReporteCajeroActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VerReporteCajero)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(69, 69, 69))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VerReporteCajero)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ventas", jPanel6);

        jTabbedPane1.addTab("Reportes", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BorrarActionPerformed
        String nombreUsuario = tf_UserNameUsuario.getText();
        if(!nombreUsuario.isEmpty())
        {
            administrador.removerUsuario(nombreUsuario);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay usuario seleccionado");
        }
        refreshTblUsuarios();
        limpiarFormUsuarios();
    }//GEN-LAST:event_btn_BorrarActionPerformed

    private void btn_SalirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirUsuarioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_SalirUsuarioActionPerformed

    private void btn_CancelarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarUsuarioActionPerformed
        limpiarFormUsuarios();
    }//GEN-LAST:event_btn_CancelarUsuarioActionPerformed

    private void btn_GuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarUsuarioActionPerformed

        if(esFormUsuariosValido())
        {
            Usuario usuario;
            boolean esAdmin = rb_Administrador.isSelected();
            if(esAdmin)
            {
                usuario = new Administrador();
            }
            else
            {
                usuario = new Cajero();
            }

            String nombre = tf_NombreUsuario.getText();
            String cedula = tf_Cedula.getText();
            String telefono = tf_Telefono.getText();

            String fechaNacimientoString = tf_FechaNacimiento.getText();
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, dateFormater);
            String sexoString = (String)cbx_Sexo.getSelectedItem();
            char sexo = sexoString.charAt(0);
            String nombreUsuario = tf_UserNameUsuario.getText();
            String password = new String(tf_Password.getPassword());

            usuario.setNombre(nombre);
            usuario.setCedula(cedula);
            usuario.setTelefono(telefono);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setSexo(sexo);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setPassword(password);

            administrador.guardarUsuario(usuario);

            refreshTblUsuarios();
            limpiarFormUsuarios();

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos");
        }

    }//GEN-LAST:event_btn_GuardarUsuarioActionPerformed

    private void tbl_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariosMouseClicked
        int indexRow = tbl_Usuarios.getSelectedRow();

        String nombreUsuario = (String)tbl_Usuarios.getValueAt(indexRow, 0);
        Usuario usuario = administrador.getUsuarioByUserName(nombreUsuario);
        tf_NombreUsuario.setText(usuario.getNombre());
        tf_Cedula.setText(usuario.getCedula());
        tf_Telefono.setText(usuario.getTelefono());
        int sexoIndex = usuario.getSexo() == 'M' ? 0 : 1;
        cbx_Sexo.setSelectedIndex(sexoIndex);
        LocalDate fechaNacimiento = usuario.getFechaNacimiento();
        String fechaNacimientoString = dateFormater.format(fechaNacimiento);
        tf_FechaNacimiento.setText(fechaNacimientoString);
        tf_UserNameUsuario.setText(nombreUsuario);
        tf_Password.setText(usuario.getPassword());
        boolean esAdmin = usuario instanceof Administrador;
        rb_Administrador.setSelected(esAdmin);
        rb_Cajero.setSelected(!esAdmin);
    }//GEN-LAST:event_tbl_UsuariosMouseClicked

    private void btn_CargarImagenComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CargarImagenComboActionPerformed
        fileChooser.setFileFilter(imageFilter);
        int opcion = fileChooser.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            Path filePath = Paths.get(file.getPath());
            
            String randomName = FileTools.getRandomFileName(file.getName());
            System.out.println(randomName);
            Path newImagePath = Paths.get(ObjetoVendible.IMAGES_PATH + randomName);
            rutaImagenCombo = newImagePath.toString();
            System.out.println(filePath);

            try {
                Files.copy(filePath, newImagePath, REPLACE_EXISTING);
                ImageTools.cargarImagenALabel(lbl_ComboImagen, file);
            } catch (IOException ex) {
                Logger.getLogger(AdministracionRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_CargarImagenComboActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        limpiarFormCombos();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_BorrarComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BorrarComboActionPerformed
        String nombreCombo = tf_NombreCombo.getText();
        if(!nombreCombo.isEmpty())
        {
            administrador.removerCombo(nombreCombo);
            refreshTblCombos();
            limpiarFormCombos();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay combo seleccionado");
        }
    }//GEN-LAST:event_btn_BorrarComboActionPerformed

    private void btn_GuardarComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarComboActionPerformed
        if(esFormCombosValido())
        {
            String nombreCombo = tf_NombreCombo.getText();
            Double descuentoCombo = Double.parseDouble(tf_DescuentoCombo.getText());

            comboActual.setNombre(nombreCombo);
            comboActual.setDescuento(descuentoCombo);
            comboActual.setRutaImagen(rutaImagenCombo);
            administrador.guardarCombo(comboActual);

            refreshTblCombos();
            limpiarFormCombos();
            
            

        }
        else
        {
            JOptionPane.showMessageDialog(this, "No se puede guardar combo sin producto o con algún campo vacío");
        }
    }//GEN-LAST:event_btn_GuardarComboActionPerformed

    private void btn_ModificarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarProductosActionPerformed
        ListaProductosGUI listaProductos = new ListaProductosGUI(this, true,
            administrador, comboActual.getProductos());
        listaProductos.setVisible(true);
        LinkedList<Producto> productosSeleccionados = listaProductos.getProductosSeleccionados();
        comboActual.setProductos(productosSeleccionados);

        refreshTblProductosCombo();
    }//GEN-LAST:event_btn_ModificarProductosActionPerformed

    private void tbl_CombosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CombosMouseClicked
        int indexRow = tbl_Combos.getSelectedRow();

        String nombreCombo = (String)tbl_Combos.getValueAt(indexRow, 0);
        Combo combo = administrador.getComboPorNombre(nombreCombo);
        tf_NombreCombo.setText(combo.getNombre());
        String descuentoComboString = Double.toString(combo.getDescuento());
        tf_DescuentoCombo.setText(descuentoComboString);
        comboActual.setProductos(combo.getProductos());
        File file = new File(combo.getRutaImagen());
        try{
            ImageTools.cargarImagenALabel(lbl_ComboImagen, file);
        }catch (Exception x){

        }
        refreshTblProductosCombo();
    }//GEN-LAST:event_tbl_CombosMouseClicked

    private void ckb_ProductoAgrandableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_ProductoAgrandableActionPerformed
        boolean chekeado = ckb_ProductoAgrandable.isSelected();
        pnl_ContenedorProductoAgrandable.setVisible(chekeado);
    }//GEN-LAST:event_ckb_ProductoAgrandableActionPerformed

    private void btn_SalirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirProductoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_SalirProductoActionPerformed

    private void btn_CancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarProductoActionPerformed
        limpiarFormProducto();
    }//GEN-LAST:event_btn_CancelarProductoActionPerformed

    private void btn_BorrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BorrarProductoActionPerformed
        String nombreProducto = tf_NombreProducto.getText();
        if(!nombreProducto.isEmpty())
        {
            administrador.removerProducto(nombreProducto);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay producto seleccionado");
        }
        refreshTblProductos();
        limpiarFormProducto();
    }//GEN-LAST:event_btn_BorrarProductoActionPerformed

    private void btn_GuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarProductoActionPerformed
        if(esFormProductosValido())
        {
            int maximoAgrandamiento = (Integer) spn_MaximoAgrandamiento.getValue();
            Producto producto;
            boolean chekeado = ckb_ProductoAgrandable.isSelected();
            if(chekeado)
            {
                producto = new ProductoAgrandable(maximoAgrandamiento);
            }
            else
            {
                producto = new Producto();
            }

            producto.setRutaImagen(rutaImagenProducto);
            producto.setNombre(tf_NombreProducto.getText());
            producto.setPrecio(Double.parseDouble(tf_PrecioProducto.getText()));
            producto.setDescuento(Double.parseDouble(tf_DescuentoProducto.getText()));

            administrador.guardarProducto(producto);
            refreshTblProductos();
            limpiarFormProducto();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "El nombre y el precio son obligatorios");
        }
    }//GEN-LAST:event_btn_GuardarProductoActionPerformed

    private void btn_CargarImagenProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CargarImagenProductoActionPerformed
        fileChooser.setFileFilter(imageFilter);
        int opcion = fileChooser.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            
            String randomName = FileTools.getRandomFileName(file.getName());
            
            Path filePath = Paths.get(file.getPath());
            Path newImagePath = Paths.get(ObjetoVendible.IMAGES_PATH + randomName);
            rutaImagenProducto = newImagePath.toString();
            System.out.println(filePath);

            try {
                Files.copy(filePath, newImagePath, REPLACE_EXISTING);
                ImageTools.cargarImagenALabel(lbl_ProductoImagen, file);
            } catch (IOException ex) {
                Logger.getLogger(AdministracionRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_CargarImagenProductoActionPerformed

    private void tbl_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ProductosMouseClicked
        int indexRow = tbl_Productos.getSelectedRow();

        String nombreProducto = (String) tbl_Productos.getValueAt(indexRow, 0);
        Producto producto = administrador.getProductoPorNombre(nombreProducto);
        if (producto instanceof ProductoAgrandable){
            ckb_ProductoAgrandable.setSelected(true);
            pnl_ContenedorProductoAgrandable.setVisible(true);
            ProductoAgrandable productoAgrandable = (ProductoAgrandable)producto;
            spn_MaximoAgrandamiento.setValue((Object)productoAgrandable.getMaxAgrandados());
            System.out.println(productoAgrandable.getMaxAgrandados());
        }
        tf_NombreProducto.setText(producto.getNombre());
        tf_PrecioProducto.setText(Double.toString(producto.getPrecio()));
        tf_DescuentoProducto.setText(Double.toString(producto.getDescuento()));

        File file = new File(producto.getRutaImagen());
        try{
            ImageTools.cargarImagenALabel(lbl_ProductoImagen, file);
        }catch (Exception x){

        }
    }//GEN-LAST:event_tbl_ProductosMouseClicked

    private void VerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerReporteActionPerformed

       refreshTblReporte(administrador.getReporteProductos());
    }//GEN-LAST:event_VerReporteActionPerformed

    private void salirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirReporteActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirReporteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void VerReporteCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerReporteCajeroActionPerformed
        refreshTblReporteVenta(administrador.getReporteCajero());
        /*for (int i=0;i<administrador.getReporteCajero().size();i++){
            System.out.println(administrador.getReporteCajero().get(i).getNombreCajero());
        }*/
    }//GEN-LAST:event_VerReporteCajeroActionPerformed

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
            java.util.logging.Logger.getLogger(AdministracionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionRestaurante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VerReporte;
    private javax.swing.JButton VerReporteCajero;
    private javax.swing.ButtonGroup bgr_Usuarios;
    private javax.swing.JButton btn_Borrar;
    private javax.swing.JButton btn_BorrarCombo;
    private javax.swing.JButton btn_BorrarProducto;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_CancelarProducto;
    private javax.swing.JButton btn_CancelarUsuario;
    private javax.swing.JButton btn_CargarImagenCombo;
    private javax.swing.JButton btn_CargarImagenProducto;
    private javax.swing.JButton btn_GuardarCombo;
    private javax.swing.JButton btn_GuardarProducto;
    private javax.swing.JButton btn_GuardarUsuario;
    private javax.swing.JButton btn_ModificarProductos;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_SalirUsuario;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox cbx_Sexo;
    private javax.swing.JCheckBox ckb_ProductoAgrandable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbl_ComboImagen;
    private javax.swing.JLabel lbl_PrecioTotalCombo;
    private javax.swing.JLabel lbl_ProductoImagen;
    private javax.swing.JPanel pnl_ContenedorProductoAgrandable;
    private javax.swing.JRadioButton rb_Administrador;
    private javax.swing.JRadioButton rb_Cajero;
    private javax.swing.JTable reporte;
    private javax.swing.JTable reporteCajero;
    private javax.swing.JButton salirReporte;
    private javax.swing.JSpinner spn_MaximoAgrandamiento;
    private javax.swing.JTable tbl_Combos;
    private javax.swing.JTable tbl_Productos;
    private javax.swing.JTable tbl_ProductosCombo;
    private javax.swing.JTable tbl_Usuarios;
    private javax.swing.JTextField tf_Cedula;
    private javax.swing.JFormattedTextField tf_DescuentoCombo;
    private javax.swing.JFormattedTextField tf_DescuentoProducto;
    private javax.swing.JFormattedTextField tf_FechaNacimiento;
    private javax.swing.JTextField tf_NombreCombo;
    private javax.swing.JTextField tf_NombreProducto;
    private javax.swing.JTextField tf_NombreUsuario;
    private javax.swing.JPasswordField tf_Password;
    private javax.swing.JFormattedTextField tf_PrecioProducto;
    private javax.swing.JFormattedTextField tf_Telefono;
    private javax.swing.JTextField tf_UserNameUsuario;
    // End of variables declaration//GEN-END:variables
}
