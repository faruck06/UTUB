/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utub;

//import emab.admon.Adm_empleados;
//import emab.admon.Adm_vehiculos;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;
import view.Administracion.frm_Actividad_Novedad;
import view.Administracion.frm_Actividad_Principal;
import view.Administracion.frm_Empleado;
import view.Administracion.frm_Horario;
import view.Administracion.frm_Proyecto;
import view.Administracion.frm_Rutas;
import view.Administracion.frm_Tipo_Empleado;
import view.Administracion.frm_Tipo_Vehiculo;
import view.Administracion.frm_Usuario_Proyecto;
import view.Administracion.frm_Vehiculo;
import view.Facturacion.frm_Consultar_Reporte_Diario;
import view.Facturacion.frm_Ingreso_Reporte_C;
import view.Facturacion.Reportes.Facturacion_1;

/**
 *
 * @author Jhorman
 */
public class MainWindow extends javax.swing.JFrame {

    boolean estado_Menu;
    String rol_usuario;
    public static final int V_HEIGHT = 611;
    public static final int V_WIDTH = 1066;

    public MainWindow(Boolean estado) {

//        if (estado) {
//            cambiarVentana();
//        }
    }

    public MainWindow() {

        initComponents();
        Fondo fondo = new Fondo();
        abrir_ventana(fondo);
//        ventanaInicial();
//        quitar_icono();
    }

//    private void ventanaInicial() {
//        FondoIndex.removeAll();
//        final Navegacion navegacion = new Navegacion();
//        navegacion.setBounds(0, 0, V_WIDTH, V_HEIGHT);
//       FondoIndex.add(navegacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
//        ocultar_Admon();
//        ocultar_reportes();
//        navegacion.addButton1ActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                rol_usuario = navegacion.consultar2();
//                if (rol_usuario.equals("")) {
//                    setEstado(false);
//                } else {
//                    setEstado(true);
//                }
//                //setEstado(navegacion.consultar());
//            }
//        });
//   }
    public void abrir_ventana(JPanel panel) {
        FondoIndex.removeAll();
        panel.setBounds(0, 0, V_WIDTH, V_HEIGHT);
        FondoIndex.add(panel, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FondoIndex = new javax.swing.JLayeredPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menu_Reportes = new javax.swing.JMenu();
        menu_reporte1 = new javax.swing.JMenuItem();
        menu_reporte2 = new javax.swing.JMenuItem();
        menu_reporte3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema información Transportes Rio Grande LTDA");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);
        setResizable(false);

        FondoIndex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FondoIndex.setPreferredSize(new java.awt.Dimension(1004, 680));

        javax.swing.GroupLayout FondoIndexLayout = new javax.swing.GroupLayout(FondoIndex);
        FondoIndex.setLayout(FondoIndexLayout);
        FondoIndexLayout.setHorizontalGroup(
            FondoIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1065, Short.MAX_VALUE)
        );
        FondoIndexLayout.setVerticalGroup(
            FondoIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );

        jMenu1.setText("Inicio");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Administración");

        jMenuItem3.setText("Personal");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Vehiculos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Rutas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Proyectos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Horarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Tipo de Vehiculos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Actividad Novedad");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Actividad Principal");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Usuario Proyecto");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Tipo Usuario");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuBar2.add(jMenu2);

        jMenu3.setText("Facturación");

        jMenuItem2.setText("Crear Reporte Diario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem1.setText("Consultar Reporte Diario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        menu_Reportes.setBorder(null);
        menu_Reportes.setText(" Reportes");
        menu_Reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ReportesActionPerformed(evt);
            }
        });

        menu_reporte1.setText("Reporte 1");
        menu_reporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reporte1ActionPerformed(evt);
            }
        });
        menu_Reportes.add(menu_reporte1);

        menu_reporte2.setText("Reporte 2");
        menu_reporte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reporte2ActionPerformed(evt);
            }
        });
        menu_Reportes.add(menu_reporte2);

        menu_reporte3.setText("Reporte 3");
        menu_reporte3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reporte3ActionPerformed(evt);
            }
        });
        menu_Reportes.add(menu_reporte3);

        jMenu3.add(menu_Reportes);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FondoIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FondoIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frm_Ingreso_Reporte_C frm = new frm_Ingreso_Reporte_C();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        frm_Empleado frm = new frm_Empleado();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        frm_Vehiculo frm = new frm_Vehiculo();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        frm_Rutas frm = new frm_Rutas();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frm_Proyecto frm = new frm_Proyecto();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        frm_Horario frm = new frm_Horario();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        frm_Tipo_Vehiculo frm = new frm_Tipo_Vehiculo();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        frm_Actividad_Novedad frm = new frm_Actividad_Novedad();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        frm_Actividad_Principal frm = new frm_Actividad_Principal();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        frm_Usuario_Proyecto frm = new frm_Usuario_Proyecto();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        frm_Tipo_Empleado frm = new frm_Tipo_Empleado();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frm_Consultar_Reporte_Diario frm = new frm_Consultar_Reporte_Diario();
        abrir_ventana(frm);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menu_reporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reporte1ActionPerformed
        Facturacion_1 f = new Facturacion_1();
        abrir_ventana(f);
    }//GEN-LAST:event_menu_reporte1ActionPerformed

    private void menu_ReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_ReportesActionPerformed

    private void menu_reporte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reporte2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_reporte2ActionPerformed

    private void menu_reporte3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reporte3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_reporte3ActionPerformed

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
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

//    private void quitar_icono() {
//        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
//        this.setIconImage(icon);
//    }
    public void setIconImage(Image image) {
        ArrayList<Image> imageList = new ArrayList<Image>();
        if (image != null) {
            imageList.add(image);
        }
        setIconImages(imageList);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane FondoIndex;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu menu_Reportes;
    private javax.swing.JMenuItem menu_reporte1;
    private javax.swing.JMenuItem menu_reporte2;
    private javax.swing.JMenuItem menu_reporte3;
    // End of variables declaration//GEN-END:variables
}
