package view.Facturacion;

import JPA.ActividadNovedad;
import JPA.ActividadPrincipal;
import JPA.Empleado;
import JPA.Horario;
import JPA.Novedad;
import JPA.Proyecto;
import JPA.ReporteDiario;
import JPA.Ruta;
import JPA.RutaExterna;
import JPA.RutaHorario;
import JPA.RutaOcupacion;
import JPA.ServicioIndividual;
import JPA.UsuarioProyecto;
import JPA.Vehiculo;
import data.Cls_Datos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import src.Genericas;

/**
 *
 * @author Developer
 */
public class frm_Ingreso_Reporte_C extends javax.swing.JPanel {

    Cls_Datos cls;
    Genericas genericas = new Genericas();
    ReporteDiario reporteDiario;
    Empleado empleado;
    Vehiculo vehiculo;
    ActividadPrincipal actividadPrincipal;
    UsuarioProyecto usuarioProyecto;
    Proyecto proyecto;
    List<RutaHorario> listaRutaHorarios;
    List<RutaExterna> listaRutaExternas;
    List<RutaOcupacion> listaRutaOcupacion;
    List<Novedad> listaNovedades;
    List<ServicioIndividual> listaServicios;
    Date date = new Date();

    /**
     * Metodo que captura las cajas de texto seleccionadas.
     */
    public void guardar() {

        try {

            listaRutaHorarios = new ArrayList<>();
            listaRutaExternas = new ArrayList<>();
            listaRutaOcupacion = new ArrayList<>();
            listaNovedades = new ArrayList<>();
            listaServicios = new ArrayList<>();
            this.empleado.setCedula(genericas.getIdCombo(comboEmpleado));
            reporteDiario.setIdEmpleado(this.empleado);

            this.vehiculo.setPlaca(genericas.getTextoCombo(comboPlaca));
            reporteDiario.setPlaca(this.vehiculo);

            this.actividadPrincipal.setIdActividadPrincipal(genericas.getIdComboLong(comboActividad));
            reporteDiario.setIdActividadPrincipal(this.actividadPrincipal);

            this.usuarioProyecto.setIdUsuarioProyecto(genericas.getIdComboLong(comboUsuario));
            reporteDiario.setIdUsuarioProyecto(this.usuarioProyecto);

            this.proyecto.setIdProyecto(genericas.getIdComboLong(comboProyecto));
            reporteDiario.setIdProyecto(this.proyecto);
            SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
            Date fechaReporteDiario = null;
            Date HoraInicio = null;
            Date HoraFin = null;
            try {
                fechaReporteDiario = df.parse(webDateField1.toString().trim());
                HoraInicio = df.parse(HInicio.toString().trim());
                HoraFin = df.parse(spinner.toString().trim());
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se puede agregar la hora fecha del reporte diario");
            }
            reporteDiario.setFecha(fechaReporteDiario);
            reporteDiario.setHoraFin(HoraFin);
            reporteDiario.setHoraInicio(HoraInicio);
            reporteDiario.setKmFinal(Double.parseDouble(jTextField2.getText().trim()));
            reporteDiario.setKmInicial(Double.parseDouble(jTextField1.getText().trim()));
            obtenerValoresListados();
            reporteDiario.setServicioIndividualCollection(listaServicios);
            reporteDiario.setNovedadCollection(listaNovedades);
            reporteDiario.setRutaExternaCollection(listaRutaExternas);
            reporteDiario.setRutaHorarioCollection(listaRutaHorarios);
            reporteDiario.setRutaOcupacionCollection(listaRutaOcupacion);

            //reporteDiario.setObservaciones("Faruck obs");
            String mensaje = genericas.guardarBD(reporteDiario);
            if (mensaje.equals("")) {
                JOptionPane.showMessageDialog(null, "Se han guardado exitosamente los registros");
            } else {
                JOptionPane.showMessageDialog(null, mensaje);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getListadoServicioIndividual() {

        Object[][] objeto = getTableData(jTable5);
        for (Object[] itera : objeto) {
            if (itera[0] != null) {
                ServicioIndividual servicio = new ServicioIndividual();
                servicio.setIdReporteDiario(this.reporteDiario);
                servicio.setIdRuta(new Ruta(genericas.getIdFromText(itera[2].toString())));
                servicio.setNombres(itera[0].toString().toUpperCase());
                servicio.setRegistro(itera[1].toString().toUpperCase());
                listaServicios.add(servicio);

            }
        }
    }

    public void getListadoNovedades() {

        Object[][] objeto = getTableData(jTable4);
        for (Object[] itera : objeto) {
            if (itera[0] != null) {
                Novedad novedad = new Novedad();
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                try {
                    fechaInicio = df.parse(itera[0].toString().trim());
                    fechaFin = df.parse(itera[1].toString().trim());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se puede agregar la hora de Inicio/Fin de la novedad");
                }
                novedad.setHoraInicio(fechaInicio);
                novedad.setHoraFinal(fechaFin);
                novedad.setDuracion(Double.parseDouble(itera[2].toString()));
                novedad.setKmInicial(Integer.parseInt(itera[3].toString()));
                novedad.setKmFinal(Integer.parseInt(itera[4].toString()));
                novedad.setTotalKm(Integer.parseInt(itera[5].toString()));
                novedad.setIdNovedadActividad(new ActividadNovedad(genericas.getIdFromText(itera[6].toString())));
                novedad.setIdReporteDiario(this.reporteDiario);
                this.listaNovedades.add(novedad);
            }
        }
    }

    public void getListadoRutaOcupacion() {

        Object[][] objeto = getTableData(jTable3);
        for (Object[] itera : objeto) {
            if (itera[0] != null) {
                RutaOcupacion ruta = new RutaOcupacion();
                ruta.setIdReporteDiario(this.reporteDiario);
                ruta.setIdRuta(new Ruta(genericas.getIdFromText(itera[0].toString())));
                ruta.setRecorridosMañana(Integer.parseInt(itera[1].toString()));
                ruta.setOcupacionMañana(Integer.parseInt(itera[2].toString()));
                ruta.setRecorridosTarde(Integer.parseInt(itera[3].toString()));
                ruta.setOcupacionTarde(Integer.parseInt(itera[4].toString()));
                ruta.setRecorridosNoche(Integer.parseInt(itera[5].toString()));
                ruta.setOcupacionNoche(Integer.parseInt(itera[6].toString()));
                this.listaRutaOcupacion.add(ruta);

            }
        }
    }

    public void getListadoRutasExternas() {
        Object[][] objeto = getTableData(jTable2);
        for (Object[] itera : objeto) {
            if (itera[1] != null) {
                Date fechaValor = null;

                SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                try {
                    fechaValor = df.parse(itera[2].toString().trim());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se puede agregar la hora de llegada la fecha externa seleccionada.");
                }

                RutaExterna rutaExterna = new RutaExterna();

                rutaExterna.setHoraLlegada(fechaValor);
                rutaExterna.setIdReporteDiario(this.reporteDiario);
                if (itera[1].toString().equals("Entrada")) {
                    rutaExterna.setIngreso("S");
                    rutaExterna.setSalida("N");
                } else {
                    rutaExterna.setIngreso("N");
                    rutaExterna.setSalida("S");
                }
                rutaExterna.setOcupacion(Short.parseShort(itera[3].toString()));
                //obtengo el combo de la tabla

                rutaExterna.setIdRuta(new Ruta(genericas.getIdFromText(itera[0].toString())));
                this.listaRutaExternas.add(rutaExterna);
            }
        }
    }

    public void getListadoRutasCirculares() {

        Object[][] objeto = getTableData(jTable1);
        for (Object[] itera : objeto) {
            if (itera[1] != null) {
                RutaHorario rutaHorario = new RutaHorario();
                rutaHorario.setIdHorario(new Horario(getIDRutaHorario((String) itera[0])));
                rutaHorario.setIdReporteDiario(this.reporteDiario);
                rutaHorario.setIdRuta(new Ruta(genericas.getIdComboLong(comboCircular)));
                rutaHorario.setOcupacion(Short.parseShort(itera[1].toString()));
                this.listaRutaHorarios.add(rutaHorario);
            }
        }
    }

    public Long getIDRutaHorario(String nombre) {
        return cls.getIdRutaHorario(nombre);
    }

    public Object[][] getTableData(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j);
            }
        }
        return tableData;
    }

    public frm_Ingreso_Reporte_C() {
        initComponents();
        this.reporteDiario = new ReporteDiario();
        this.empleado = new Empleado();
        this.vehiculo = new Vehiculo();
        this.actividadPrincipal = new ActividadPrincipal();
        this.usuarioProyecto = new UsuarioProyecto();
        this.proyecto = new Proyecto();
        listaRutaHorarios = new ArrayList<>();
        listaRutaExternas = new ArrayList<>();
        listaRutaOcupacion = new ArrayList<>();
        listaNovedades = new ArrayList<>();
        listaServicios = new ArrayList<>();
        inicializar_combos();
    }

    public frm_Ingreso_Reporte_C(ReporteDiario reporteDiario) {
        initComponents();
        inicializar_combos();
    }

    private void inicializar_combos() {
        cls = new Cls_Datos();
        generar_Listener_Combo_empleados((JTextField) comboEmpleado.getEditor().getEditorComponent(), comboEmpleado);
        generar_Listener_Combo_Vehiculos((JTextField) comboPlaca.getEditor().getEditorComponent(), comboPlaca);
        generar_Listener_Combo_Proyecto((JTextField) comboProyecto.getEditor().getEditorComponent(), comboProyecto);
        generar_Listener_Combo_Actividad((JTextField) comboActividad.getEditor().getEditorComponent(), comboActividad);
        generar_Listener_Combo_Usuarios((JTextField) comboUsuario.getEditor().getEditorComponent(), comboUsuario);
        generar_Listener_Combo_Rutas((JTextField) comboCircular.getEditor().getEditorComponent(), comboCircular);
        generar_Listener_Combo_RutasE((JTextField) comboRutaExterna.getEditor().getEditorComponent(), comboRutaExterna);
        generar_Listener_Combo_RutasOcupacion((JTextField) comboRutaOcupacion.getEditor().getEditorComponent(), comboRutaOcupacion);
        generar_Listener_Combo_ActividadNovedad((JTextField) comboActividadNovedad.getEditor().getEditorComponent(), comboActividadNovedad);
        generar_Listener_Combo_RutaServicioIndvidual((JTextField) comboRutaIndividual.getEditor().getEditorComponent(), comboRutaIndividual);
        generar_Listener_Horario_Inicial();
        generar_Listener_Combo_Tipos();
    }

    public void generar_Listener_Combo_Tipos() {
        javax.swing.table.TableColumn tc = this.jTable2.getColumnModel().getColumn(1);
        TableCellEditor tce = new DefaultCellEditor(comboTipo);
        tc.setCellEditor(tce);
    }

    public void generar_Listener_Combo_RutasE(final JTextField textfield, final JComboBox combo) {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoRutasTipos("Externa");
        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        javax.swing.table.TableColumn tc = this.jTable2.getColumnModel().getColumn(0);
        TableCellEditor tce = new DefaultCellEditor(combo);
        tc.setCellEditor(tce);
    }

    public void generar_Listener_Combo_RutasOcupacion(final JTextField textfield, final JComboBox combo) {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoRutasTipos("Ocupacion");
        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        javax.swing.table.TableColumn tc = this.jTable3.getColumnModel().getColumn(0);
        TableCellEditor tce = new DefaultCellEditor(combo);
        tc.setCellEditor(tce);

    }

    public void generar_Listener_Combo_RutaServicioIndvidual(final JTextField textfield, final JComboBox combo) {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoRutasTipos("Servicio Individual");
        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        javax.swing.table.TableColumn tc = this.jTable5.getColumnModel().getColumn(2);
        TableCellEditor tce = new DefaultCellEditor(combo);
        tc.setCellEditor(tce);

    }

    public void generar_Listener_Combo_ActividadNovedad(final JTextField textfield, final JComboBox combo) {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoActividadNovedad();
        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        javax.swing.table.TableColumn tc = this.jTable4.getColumnModel().getColumn(6);
        TableCellEditor tce = new DefaultCellEditor(combo);
        tc.setCellEditor(tce);

    }

    public void generar_Listener_Horario_Inicial() {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoHorarios();

        for (int x = 0; x < filterArray.size(); x++) {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm a");
            this.jTable1.setValueAt(formato.format(filterArray.get(x)), x, 0);

        }

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

    public void generar_Listener_Combo_Rutas(final JTextField textfield, final JComboBox combo) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        filter_combo_Rutas(textfield.getText(), combo);
                    }
                });
            }
        });
    }

    public void filter_combo_Rutas(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoRutas(enteredText, "Circular");
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

        jLabel1 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboEmpleado = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        comboPlaca = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        comboActividad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        comboProyecto = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        diferencia = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        comboCircular = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new javax.swing.JSpinner(sm);
        SpinnerDateModel sms = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        HInicio = new javax.swing.JSpinner(sms);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Reporte Diario");

        webDateField1.setToolTipText("Fecha");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fecha");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Empleado");

        comboEmpleado.setEditable(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Placa");

        comboPlaca.setEditable(true);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Actividad");

        comboActividad.setEditable(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Proyecto");

        comboProyecto.setEditable(true);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Usuario");

        comboUsuario.setEditable(true);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Km Inicial");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Km Final");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tanqueo # Galones");

        jTextField3.setToolTipText("Galones");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Hora Inicio");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Hora Final");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Duración");

        diferencia.setToolTipText("Hora ( HH:24)");
        diferencia.setEnabled(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Ruta Circular ");

        comboCircular.setEditable(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Horario", "Ocupación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCircular, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(635, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(comboCircular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ocupación Rutas Circulares", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ruta", "Tipo", "Hora llegada", "Ocupación Pasajeros"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ocupación Rutas Externas", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Ruta", "# Recorridos Mañana", "Ocupación", "# Recorridos Tarde", "Ocupación", "# Recorridos Noche", "Ocupación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(5).setResizable(false);
            jTable3.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ruta Ocupación", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Hora Inicio", "Hora Final", "Duración", "Km Inicial", "Km Final", "Total Km", "Actividad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getColumnModel().getColumn(1).setResizable(false);
            jTable4.getColumnModel().getColumn(2).setResizable(false);
            jTable4.getColumnModel().getColumn(3).setResizable(false);
            jTable4.getColumnModel().getColumn(4).setResizable(false);
            jTable4.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Novedades", jPanel4);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre y Apellido", "Registro", "Ruta"
            }
        ));
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Servicio Individual", jPanel5);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Observaciones:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Observaciones", jPanel6);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm a");
        de.getTextField().setEditable( true );
        spinner.setEditor(de);
        spinner.addChangeListener(new MyFocusListener());

        JSpinner.DateEditor des = new JSpinner.DateEditor(HInicio, "hh:mm a");
        des.getTextField().setEditable( true );
        HInicio.setEditor(des);
        HInicio.addChangeListener(new MyFocusListener());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
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
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5)))
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
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel8)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel11)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(HInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel13)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(22, 22, 22))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        obtenerValoresListados();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void obtenerValoresListados() {
        this.reporteDiario.setObservaciones(jTextArea1.getText().trim());
        getListadoServicioIndividual();
        getListadoNovedades();
        getListadoRutaOcupacion();
        getListadoRutasExternas();
        getListadoRutasCirculares();
    }

    private class MyFocusListener implements ChangeListener {

        public MyFocusListener() {
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            String texto = calcularDiferenciaHoras((Date) HInicio.getModel().getValue(), (Date) spinner.getModel().getValue());
            diferencia.setText(texto);
        }

    }

    public String calcularDiferenciaHoras(Date date1, Date date2) {

        Map<TimeUnit, Long> mapa = computeDiff(date1, date2);
        Long horas = mapa.get(TimeUnit.HOURS);
        Long minutos = mapa.get(TimeUnit.MINUTES);

        Double calculo = (minutos.doubleValue() / 60);
        if (calculo == 1) {
            calculo = 0.00;
        }
        calculo += horas.doubleValue();
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(calculo);
    }

    public static Map<TimeUnit, Long> computeDiff(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
        long milliesRest = diffInMillies;
        for (TimeUnit unit : units) {
            long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }
        return result;
    }
    private JComboBox comboRutaExterna = new JComboBox();
    private JComboBox comboRutaOcupacion = new JComboBox();
    private JComboBox comboActividadNovedad = new JComboBox();
    private JComboBox comboRutaIndividual = new JComboBox();
    final String[] tipos = {"Entrada", "Salida"};
    private JComboBox comboTipo = new JComboBox(tipos);
    //private JSpinner spinnerHoraTabla;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner HInicio;
    private javax.swing.JComboBox comboActividad;
    private javax.swing.JComboBox comboCircular;
    private javax.swing.JComboBox comboEmpleado;
    private javax.swing.JComboBox comboPlaca;
    private javax.swing.JComboBox comboProyecto;
    private javax.swing.JComboBox comboUsuario;
    private javax.swing.JTextField diferencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JSpinner spinner;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables

}
