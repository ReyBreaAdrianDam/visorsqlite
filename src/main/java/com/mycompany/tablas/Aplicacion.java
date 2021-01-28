/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tablas;
import com.formdev.flatlaf.intellijthemes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author adrir
 */
public class Aplicacion extends javax.swing.JFrame {

    /**
     * Creates new form Aplicacion
     */
    Modelo modelo;
    File baseDeDatos;
    /**
     * Constructor de la aplicacion
     */
    public Aplicacion() {
        this.setTitle("Tablas");
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
        initInformacion();
        generarArbol();

    }
    /**
     * Metodo que añade imagen al icono
     * @return 
     */
    @Override
    public Image getIconImage() {
        URL imageResource = Aplicacion.class.getClassLoader().getResource("sqlite.png");
        Image retValue = Toolkit.getDefaultToolkit().getImage(imageResource);
        return retValue;
    }
    
    /**
     * Metodo para ejecutar consultas
     */
    public void ejecutarConsulta() {
        try {

            if (EjecutarConsulta.Ejecutar(jTextArea1.getText(), baseDeDatos)) {
                jTable1.setModel(new Modelo(baseDeDatos, modelo.getTablaActual(), jTextArea1.getText()));
            } else {
                try{
                    jTable1.setModel(new Modelo(baseDeDatos, modelo.getTablaActual()));
                }
                catch(Exception ex){
                    jTable1.setModel(new Modelo(baseDeDatos));
                }
            }
            initInformacion();
            generarArbol();
        } catch (SQLException ignored) {
        }

    }
    /**
     * Metodo para generar el arbol de directorios
     */
    private void generarArbol() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(modelo.getFichero().getName());
        for (String x : modelo.getTablas()) {
            DefaultMutableTreeNode v = new DefaultMutableTreeNode(x);
            root.add(v);
        }
        jTree1 = new JTree(root);
        jScrollPane2.setViewportView(jTree1);
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                String tabla = evt.getNewLeadSelectionPath().getLastPathComponent().toString();
                if (!tabla.contains(modelo.getFichero().getName())) {
                    jTable1.setModel(new Modelo(modelo.getFichero(), tabla));
                    initInformacion();
                }
            }
        });
    }
    
    public File getBaseDeDatos() {
        return baseDeDatos;
    }

    public void setBaseDeDatos(File baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }
    /**
     * Metodo para generar la informacion de la aplicacion y demas cosas
     */
      private void initInformacion() {
        jMenuTablas.removeAll();
        modelo = (Modelo) jTable1.getModel();
        tablaSelec.setText("Tabla seleccionada: " + modelo.getTablaActual());
        baseSelec.setText("Tabla sel.: " + modelo.getFichero().getName());
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            JTableHeader th = jTable1.getTableHeader();
            th.setFont(new Font("Arial", Font.BOLD, 14));
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(i);
            try {
                tc.setHeaderValue(modelo.getColumnas().get(i));
            } catch (Exception ex) {
            }
            th.repaint();
            for (int x = 0; x < jTable1.getModel().getColumnCount(); x++) {
                DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
                dtr.setHorizontalAlignment(JLabel.CENTER);
                jTable1.getColumnModel().getColumn(x).setCellRenderer(dtr);
                baseDeDatos = modelo.getFichero();
            }
        }
        for (String x : modelo.getTablas()) {
            JMenuItem tabSel = new JMenuItem(x);
            jMenuTablas.add(tabSel);
            tabSel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jTable1.setModel(new Modelo(modelo.getFichero(), x));
                    initInformacion();//To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }

    /**
     * Metodo autogenerado por netbeans iug
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        enviarInfo = new javax.swing.JButton();
        tablaSelec = new javax.swing.JLabel("", SwingConstants.CENTER);
        baseSelec = new javax.swing.JLabel("", SwingConstants.CENTER);
        jBorrarTexto = new javax.swing.JButton();
        menuAplicacion = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jAbrir = new javax.swing.JMenuItem();
        jCerrar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jOscuro = new javax.swing.JMenuItem();
        jClaro = new javax.swing.JMenuItem();
        jAltoContraste = new javax.swing.JMenuItem();
        jThemeDefault = new javax.swing.JMenuItem();
        jMenuTablas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(850, 350));

        jTable1.setModel(new Modelo());
        jTable1.setName(""); // NOI18N
        jTable1.setShowGrid(false);
        //jTable1.setFont(new Font("Courier new", Font.BOLD, 14));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jTree1);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        enviarInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        enviarInfo.setText("Ejecutar");
        enviarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarInfoActionPerformed(evt);
            }
        });

        tablaSelec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaSelec.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tablaSelec.setToolTipText("");

        baseSelec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        baseSelec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        baseSelec.setAlignmentY(0.0F);

        jBorrarTexto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBorrarTexto.setText("Borrar texto");
        enviarInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBorrarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBorrarTextoActionPerformed(evt);
            }
        });

        menuAplicacion.setFont(new Font("Courier new", Font.BOLD, 14));

        jMenu1.setText("Archivo");

        jAbrir.setText("Abrir...");
        jAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(jAbrir);

        jCerrar.setText("Cerrar");
        jCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCerrarActionPerformed(evt);
            }
        });
        jMenu1.add(jCerrar);

        menuAplicacion.add(jMenu1);

        jMenu2.setText("Temas");

        jOscuro.setText("Oscuro");
        jOscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOscuroActionPerformed(evt);
            }
        });
        jMenu2.add(jOscuro);

        jClaro.setText("Claro");
        jClaro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClaroActionPerformed(evt);
            }
        });
        jMenu2.add(jClaro);

        jAltoContraste.setText("Alto contraste");
        jAltoContraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltoContrasteActionPerformed(evt);
            }
        });
        jMenu2.add(jAltoContraste);

        jThemeDefault.setText("Por Defecto");
        jThemeDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThemeDefaultActionPerformed(evt);
            }
        });
        jMenu2.add(jThemeDefault);

        menuAplicacion.add(jMenu2);

        jMenuTablas.setText("Tablas");
        menuAplicacion.add(jMenuTablas);

        setJMenuBar(menuAplicacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enviarInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBorrarTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tablaSelec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enviarInfo)
                        .addComponent(jBorrarTexto))
                    .addComponent(tablaSelec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(baseSelec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    
    private void jAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbrirActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Bases de datos *.db", "db"));
        int returnVal = fc.showOpenDialog(Aplicacion.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                jTable1.setModel(new Modelo(file));
                initInformacion();
                generarArbol();
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Fichero no valido, o base de datos sin tablas", "ERROR de archivo", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jAbrirActionPerformed

    private void jCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jCerrarActionPerformed

    private void jOscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOscuroActionPerformed
        try {
            if(!this.isUndecorated()){
                this.dispose();
                this.setUndecorated(true);
                this.setVisible(true);
            }
            UIManager.setLookAndFeel(new FlatMonocaiIJTheme());
        } catch (UnsupportedLookAndFeelException ingored) {/*No deberia de pasar nada*/
        }
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jOscuroActionPerformed

    private void jClaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClaroActionPerformed
        try {
            if(!this.isUndecorated()){
                this.dispose();
                this.setUndecorated(true);
                this.setVisible(true);
            }
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
        } catch (UnsupportedLookAndFeelException ingored) {/*No deberia de pasar nada*/
        }
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jClaroActionPerformed

    private void jAltoContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltoContrasteActionPerformed
        try {
            if(!this.isUndecorated()){
                this.dispose();
                this.setUndecorated(true);
                this.setVisible(true);
            }
            UIManager.setLookAndFeel(new FlatHighContrastIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ingored) {/*No deberia de pasar nada*/
        }
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jAltoContrasteActionPerformed

    private void enviarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarInfoActionPerformed
        
        String sentencia = jTextArea1.getText().replace("\n", " ");
        switch(sentencia.toLowerCase()){
            case "hello there!":
                JOptionPane.showMessageDialog(null, "General Kenobi", "General Grievous", JOptionPane.DEFAULT_OPTION);
                break;
            case "its over anakin! i have the high ground":
                JOptionPane.showMessageDialog(null, "You understimate my power", "Anakin", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "Don't try it", "Kenobi", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "GRAAAAAH", "Anakin", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "You were the chosen one! It was said thay you would, destroy the Sith, not join them", "Kenobi", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "It was you who would bring balance to the Force, not leave it in darkness", "Kenobi", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "I HATE YOU", "Anakin", JOptionPane.DEFAULT_OPTION);
                JOptionPane.showMessageDialog(null, "You were my brother, Anakin. I loved you", "Kenobi", JOptionPane.DEFAULT_OPTION);
                break;
            default:
                 ejecutarConsulta();
                 break;
        }
        
        
       
    }//GEN-LAST:event_enviarInfoActionPerformed

    private void jThemeDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThemeDefaultActionPerformed

        try {
            if(this.isUndecorated()){
                this.dispose();
                this.setUndecorated(false);
                this.setVisible(true);
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jThemeDefaultActionPerformed

    private void jBorrarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBorrarTextoActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText(null);
    }//GEN-LAST:event_jBorrarTextoActionPerformed
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatMonocaiIJTheme());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel baseSelec;
    private javax.swing.JButton enviarInfo;
    private javax.swing.JMenuItem jAbrir;
    private javax.swing.JMenuItem jAltoContraste;
    private javax.swing.JButton jBorrarTexto;
    private javax.swing.JMenuItem jCerrar;
    private javax.swing.JMenuItem jClaro;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuTablas;
    private javax.swing.JMenuItem jOscuro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem jThemeDefault;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuBar menuAplicacion;
    private javax.swing.JLabel tablaSelec;
    // End of variables declaration//GEN-END:variables
}
