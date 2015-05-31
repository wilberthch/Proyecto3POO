/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Modelo.Productos.Producto;
import Modelo.Restaurante;
import Modelo.Usuarios.Administrador;
import Modelo.Usuarios.Cajero;
import Modelo.Usuarios.Usuario;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Shape;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    private String rutaImagen;
    
    
    
    private static final DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG,PNG, GIF Images", "jpg","png", "gif");
    
    
    
    /**
     * Creates new form AdministracionRestaurante
     */
    public AdministracionRestaurante() {
        initComponents();
        usuarios = new LinkedList<>();
        refreshTblUsuarios();
        refreshTblProductos();
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
            refreshTblUsuarios();
            refreshTblProductos();
            File file = new File("ProductoImagenes/default.png");
            cargarImagenALabel(lbl_ProductoImagen, file);
            
        } catch (IOException ex) {
            Logger.getLogger(AdministracionRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private BufferedImage resizeImage(BufferedImage pImagen, int width, int height) {
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) imagen.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(pImagen, 0, 0, width, height, null);
        g2d.dispose();
        return imagen;
    }
    
    private void cargarImagenALabel(JLabel pLabel, File pFile) throws IOException
    {
        BufferedImage imagen = ImageIO.read(pFile);
        BufferedImage imagenAjustada = resizeImage(imagen, pLabel.getWidth(), pLabel.getHeight());
        ImageIcon imagenIcon = new ImageIcon(imagenAjustada);
        pLabel.setIcon(imagenIcon);
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
    
    private void limpiarFormUsuarios()
    {
        tf_NombreUsuario.setText("");
        tf_Cedula.setText("");
        tf_Telefono.setText("");
        cbx_Sexo.setSelectedIndex(0);
        tf_FechaNacimiento.setText("");
        tf_UserNameUsuario.setText("");
        tf_Password.setText("");
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
        jSpinner1 = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jScrollPane2.setViewportView(tbl_Productos);

        jLabel9.setText("Nombre");

        jLabel10.setText("Precio");

        jLabel11.setText("Descuento");

        tf_PrecioProducto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));

        tf_DescuentoProducto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));

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

        btn_CancelarProducto.setText("Cancelar");

        btn_SalirProducto.setText("Salir");

        ckb_ProductoAgrandable.setText("Producto Agrandable");
        ckb_ProductoAgrandable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_ProductoAgrandableActionPerformed(evt);
            }
        });

        jLabel12.setText("Cantidad Agrandamientos");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        javax.swing.GroupLayout pnl_ContenedorProductoAgrandableLayout = new javax.swing.GroupLayout(pnl_ContenedorProductoAgrandable);
        pnl_ContenedorProductoAgrandable.setLayout(pnl_ContenedorProductoAgrandableLayout);
        pnl_ContenedorProductoAgrandableLayout.setHorizontalGroup(
            pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ContenedorProductoAgrandableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        pnl_ContenedorProductoAgrandableLayout.setVerticalGroup(
            pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContenedorProductoAgrandableLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnl_ContenedorProductoAgrandableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
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

    private void btn_SalirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirUsuarioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_SalirUsuarioActionPerformed

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

    private void btn_CargarImagenProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CargarImagenProductoActionPerformed
        fileChooser.setFileFilter(imageFilter);
        int opcion = fileChooser.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            Path filePath = Paths.get(file.getPath());
            Path newImagePath = Paths.get(Restaurante.IMAGES_PATH + file.getName());
            System.out.println(filePath);
            
           
            try {
                Files.copy(filePath, newImagePath, REPLACE_EXISTING);
                cargarImagenALabel(lbl_ProductoImagen, file);
            } catch (IOException ex) {
                Logger.getLogger(AdministracionRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_CargarImagenProductoActionPerformed

    private void btn_GuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarProductoActionPerformed
        if(esFormProductosValido())
        {
            
        }
    }//GEN-LAST:event_btn_GuardarProductoActionPerformed

    private void ckb_ProductoAgrandableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_ProductoAgrandableActionPerformed
        boolean chekeado = ckb_ProductoAgrandable.isSelected();
        pnl_ContenedorProductoAgrandable.setVisible(chekeado);
    }//GEN-LAST:event_ckb_ProductoAgrandableActionPerformed

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
    private javax.swing.ButtonGroup bgr_Usuarios;
    private javax.swing.JButton btn_Borrar;
    private javax.swing.JButton btn_BorrarProducto;
    private javax.swing.JButton btn_CancelarProducto;
    private javax.swing.JButton btn_CancelarUsuario;
    private javax.swing.JButton btn_CargarImagenProducto;
    private javax.swing.JButton btn_GuardarProducto;
    private javax.swing.JButton btn_GuardarUsuario;
    private javax.swing.JButton btn_SalirProducto;
    private javax.swing.JButton btn_SalirUsuario;
    private javax.swing.JComboBox cbx_Sexo;
    private javax.swing.JCheckBox ckb_ProductoAgrandable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_ProductoImagen;
    private javax.swing.JPanel pnl_ContenedorProductoAgrandable;
    private javax.swing.JRadioButton rb_Administrador;
    private javax.swing.JRadioButton rb_Cajero;
    private javax.swing.JTable tbl_Productos;
    private javax.swing.JTable tbl_Usuarios;
    private javax.swing.JTextField tf_Cedula;
    private javax.swing.JFormattedTextField tf_DescuentoProducto;
    private javax.swing.JFormattedTextField tf_FechaNacimiento;
    private javax.swing.JTextField tf_NombreProducto;
    private javax.swing.JTextField tf_NombreUsuario;
    private javax.swing.JPasswordField tf_Password;
    private javax.swing.JFormattedTextField tf_PrecioProducto;
    private javax.swing.JFormattedTextField tf_Telefono;
    private javax.swing.JTextField tf_UserNameUsuario;
    // End of variables declaration//GEN-END:variables
}
