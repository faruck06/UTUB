/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Facturacion;

import JPA.ActividadPrincipal;
import JPA.Empleado;
import JPA.Proyecto;
import JPA.ReporteDiario;
import JPA.UsuarioProyecto;
import JPA.Vehiculo;
import data.Cls_Datos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import src.Genericas;
import static src.Genericas.cadena_conexion;

/**
 *
 * @author Faruck
 */
public class frm_Consultar_Reporte_Diario extends javax.swing.JPanel {

    Cls_Datos cls = new Cls_Datos();
    Genericas genericas = new Genericas();
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    ReporteDiario reporteDiario;

    public frm_Consultar_Reporte_Diario() {
        initComponents();
        inicializar_combos();
    }

    public void consultar() {

        this.reporteDiario = new ReporteDiario();
        //       reporteDiario.setFecha(fechaReporteDiario);
        try {
            reporteDiario.setIdActividadPrincipal(new ActividadPrincipal(genericas.getIdComboLong(comboActividad)));
        }
        catch (Exception e) {
        }

        try {
            reporteDiario.setFecha(webDateField1.getDate());
        }
        catch (Exception e) {
        }

        try {
            reporteDiario.setIdEmpleado(new Empleado(genericas.getIdCombo(comboEmpleado)));
        }
        catch (Exception e) {
        }

        try {
            reporteDiario.setPlaca(new Vehiculo(genericas.getTextoCombo(comboPlaca)));
        }
        catch (Exception e) {
        }

        try {
            reporteDiario.setIdUsuarioProyecto(new UsuarioProyecto(genericas.getIdComboLong(comboUsuario)));
        }
        catch (Exception e) {
        }

        try {
            reporteDiario.setIdProyecto(new Proyecto(genericas.getIdComboLong(comboProyecto)));
        }
        catch (Exception e) {
        }

        List<ReporteDiario> lista = new ArrayList<>();
        lista = cls.get_Listado_Reportes(reporteDiario);
//        Integer contador = 0;

        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] headers;
        headers = new String[]{"Id Reporte", "Fecha", "Empleado", "Vehículo", "Actividad", "Proyecto", "Usuario"};
        dtm.setColumnIdentifiers(headers);
        //set model into the table object
        jTable1.setModel(dtm);

        if (lista.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún registro");

        } else {
            for (ReporteDiario itera : lista) {

                dtm.addRow(new Object[]{
                    itera.getIdReporteDiario().toString(),
                    df.format(itera.getFecha()),
                    itera.getIdEmpleado().getNombre(),
                    itera.getPlaca().getPlaca(),
                    itera.getIdActividadPrincipal().getNombre(),
                    itera.getIdProyecto().getNombre(),
                    itera.getIdUsuarioProyecto().getNombre()
                });
            }
        }
    }

    public void limpiar() {

        this.comboEmpleado.removeAllItems();
        this.comboPlaca.removeAllItems();
        this.webDateField1.clear();
        this.comboActividad.removeAllItems();
        this.comboProyecto.removeAllItems();
        this.comboUsuario.removeAllItems();
        this.jTable1.setModel(new DefaultTableModel());
    }

    public void inicializar_combos() {
        cls = new Cls_Datos();
        generar_Listener_Combo_empleados((JTextField) comboEmpleado.getEditor().getEditorComponent(), comboEmpleado);
        generar_Listener_Combo_Vehiculos((JTextField) comboPlaca.getEditor().getEditorComponent(), comboPlaca);
        generar_Listener_Combo_Proyecto((JTextField) comboProyecto.getEditor().getEditorComponent(), comboProyecto);
        generar_Listener_Combo_Actividad((JTextField) comboActividad.getEditor().getEditorComponent(), comboActividad);
        generar_Listener_Combo_Usuarios((JTextField) comboUsuario.getEditor().getEditorComponent(), comboUsuario);
    }

    public void generar_Listener_Combo_empleados(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Empleados(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Empleados(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<String>();
        try {
            filterArray = cls.getListadoEmpleados(enteredText);
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            combo.setSelectedItem(enteredText);
            combo.showPopup();
        } else {
            combo.hidePopup();
        }
    }

    public void generar_Listener_Combo_Vehiculos(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Vehiculos(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Vehiculos(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoVehiculos(enteredText);
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            combo.setSelectedItem(enteredText);
            combo.showPopup();
        } else {
            combo.hidePopup();
        }
    }

    public void generar_Listener_Combo_Proyecto(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Proyecto(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Proyecto(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoProyectos(enteredText);
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            combo.setSelectedItem(enteredText);
            combo.showPopup();
        } else {
            combo.hidePopup();
        }
    }

    public void generar_Listener_Combo_Actividad(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Actividad(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Actividad(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoActividades(enteredText);
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            combo.setSelectedItem(enteredText);
            combo.showPopup();
        } else {
            combo.hidePopup();
        }
    }

    public void generar_Listener_Combo_Usuarios(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Usuarios(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Usuarios(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoUsuariosProyecto(enteredText);
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            combo.setSelectedItem(enteredText);
            combo.showPopup();
        } else {
            combo.hidePopup();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UTUBPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("UTUBPU").createEntityManager();
        reporteDiarioQuery = java.beans.Beans.isDesignTime() ? null : UTUBPUEntityManager.createQuery("SELECT r FROM ReporteDiario r");
        reporteDiarioList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : reporteDiarioQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        comboEmpleado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        comboPlaca = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        comboActividad = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        comboProyecto = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Consultar reportes diarios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fecha");

        webDateField1.setToolTipText("Fecha");

        comboEmpleado.setEditable(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Empleado");

        comboPlaca.setEditable(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Placa");

        comboActividad.setEditable(true);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Actividad");

        comboProyecto.setEditable(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Proyecto");

        comboUsuario.setEditable(true);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Usuario");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jButton1.setToolTipText("Buscar");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar.png"))); // NOI18N
        jButton2.setToolTipText("Limpiar");
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        jButton3.setToolTipText("Modificar");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ELIMINAR.png"))); // NOI18N
        jButton4.setToolTipText("Eliminar");
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jLabel2)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(jLabel4))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(229, 229, 229)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboActividad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(50, 50, 50)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(54, 54, 54)))
                                .addGap(16, 16, 16))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        consultar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ReporteDiario reporte = null;
        int indice = jTable1.getSelectedRow();
        Long id = Long.parseLong(jTable1.getValueAt(indice, 0).toString());
        try {
            javax.persistence.EntityManager entityManager = createEntityManagerFactory(cadena_conexion).createEntityManager();
            reporte = entityManager.find(ReporteDiario.class, id);
            frm_Modificar_Reporte_Diario frm = new frm_Modificar_Reporte_Diario(reporte);
            frm.setVisible(true);
            //frm.setAlwaysOnTop(true);
        }
        catch (Exception e) {
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int indice = jTable1.getSelectedRow();

        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el reporte?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            String respuesta = genericas.eliminarReporteDiario(Long.parseLong(jTable1.getValueAt(indice, 0).toString()));
            consultar();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager UTUBPUEntityManager;
    private javax.swing.JComboBox comboActividad;
    private javax.swing.JComboBox comboEmpleado;
    private javax.swing.JComboBox comboPlaca;
    private javax.swing.JComboBox comboProyecto;
    private javax.swing.JComboBox comboUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private java.util.List<JPA.ReporteDiario> reporteDiarioList;
    private javax.persistence.Query reporteDiarioQuery;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
