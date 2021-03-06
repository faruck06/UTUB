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
import com.alee.utils.SwingUtils;
import data.Cls_Datos;
import java.awt.Component;
import java.awt.HeadlessException;
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
    public boolean estado = false;

    public frm_Ingreso_Reporte_C() {
        inicial_cons();
    }

    private void inicial_cons() {
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

    /**
     * Constructor de la clase usado para el caso modificar
     *
     * @param reporteDiario
     */
    public frm_Ingreso_Reporte_C(ReporteDiario reporteDiario) {
        initComponents();
        inicializar_combos();
        onload(reporteDiario);
    }

    /**
     * Metodo que captura las cajas de texto seleccionadas.
     */
    private void onload(ReporteDiario r) {
        try {
            this.estado = true;
            jButton2.setVisible(false);
            this.reporteDiario = r;
            jButton1.setText("Modificar");
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
            SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
            webDateField1.setText(formato.format(r.getFecha()));
            comboEmpleado.setSelectedItem(r.getIdEmpleado().getNombre() + "(" + r.getIdEmpleado().getCedula() + ")");
            comboPlaca.setSelectedItem(r.getPlaca().getPlaca() + " (" + r.getPlaca().getIdTipoVehiculo().getNombre() + ")");
            comboActividad.setSelectedItem(r.getIdActividadPrincipal().getNombre() + " (" + r.getIdActividadPrincipal().getIdActividadPrincipal() + ")");
            comboProyecto.setSelectedItem(r.getIdProyecto().getNombre() + " (" + r.getIdProyecto().getIdProyecto() + ")");
            comboUsuario.setSelectedItem(r.getIdUsuarioProyecto().getNombre() + " (" + r.getIdUsuarioProyecto().getIdUsuarioProyecto() + ")");
            jTextField1.setText(r.getKmInicial().toString());
            jTextField2.setText(r.getKmFinal().toString());
            diferenciaKM.setText(r.getDiferenciaKm().toString());
            jTextField3.setText(r.getTanqueo().toString());

            if (!r.getRutaHorarioCollection().isEmpty()) {
                String ruta = "";
                for (RutaHorario itera : r.getRutaHorarioCollection()) {
                    ruta = itera.getIdRuta().getNombre() + "(" + itera.getIdRuta().getIdRuta() + ")";
                    this.comboCircular.setSelectedItem(ruta);

                }
            }

            HInicio.setValue(r.getHoraInicio());
            spinner.setValue(r.getHoraFin());
            diferencia.setText(r.getDuracion().toString());
            comboTipoRuta.setSelectedItem(r.getTipoRuta());

            //lenado de la tabla1
            for (RutaHorario itera : r.getRutaHorarioCollection()) {
                listaRutaHorarios.add(itera);
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                int nRow = dtm.getRowCount();
                for (int i = 0; i < nRow; i++) {
                    //comparamos si los valores son iguales , el de la tabla y el de la base de datos, y llenamos el campo ocupación.
                    if (jTable1.getValueAt(i, 0) != null) {
                        if (jTable1.getValueAt(i, 0).equals(formatoHora.format(itera.getIdHorario().getHoraInicio()))) {
                            jTable1.setValueAt(Integer.parseInt(String.valueOf(itera.getOcupacion())), i, 1);
                        }
                    }
                }
            }

            //llenado de la tabla 2
            for (RutaExterna itera : r.getRutaExternaCollection()) {
                listaRutaExternas.add(itera);
                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                dtm.addRow(new Object[]{itera.getIdRuta().getNombre() + "(" + itera.getIdRuta().getIdRuta() + ")", (itera.getIngreso().equals("S")) ? "Entrada" : "Salida", formatoHora.format(itera.getHoraLlegada()), itera.getOcupacion()
                });
            }

            //llenado tabla 3
            int rows = 0;
            for (RutaOcupacion itera : r.getRutaOcupacionCollection()) {
                listaRutaOcupacion.add(itera);

                jTable3.setValueAt(itera.getIdRuta().getNombre() + " (" + itera.getIdRuta().getIdRuta() + ")", rows, 0);
                jTable3.setValueAt(itera.getRecorridosMañana(), rows, 1);
                jTable3.setValueAt(itera.getOcupacionMañana(), rows, 2);
                jTable3.setValueAt(itera.getRecorridosTarde(), rows, 3);
                jTable3.setValueAt(itera.getOcupacionTarde(), rows, 4);
                jTable3.setValueAt(itera.getRecorridosNoche(), rows, 5);
                jTable3.setValueAt(itera.getOcupacionNoche(), rows, 6);
                rows++;
            }
            rows = 0;
            //llenado tabla 4
            for (Novedad itera : r.getNovedadCollection()) {
                listaNovedades.add(itera);
                DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
                dtm.addRow(new Object[]{
                    formatoHora.format(itera.getHoraInicio()), formatoHora.format(itera.getHoraFinal()), itera.getDuracion(), itera.getKmInicial(), itera.getKmFinal(), itera.getTotalKm(), itera.getIdNovedadActividad().getNombre() + "(" + itera.getIdNovedadActividad().getIdActividadNovedad() + ")"
                });
            }

            //llenado tabla 5
            for (ServicioIndividual itera : r.getServicioIndividualCollection()) {
                listaServicios.add(itera);
                jTable5.setValueAt(itera.getNombres(), rows, 0);
                jTable5.setValueAt(itera.getRegistro(), rows, 1);
                jTable5.setValueAt(itera.getIdRuta().getNombre() + " (" + itera.getIdRuta().getIdRuta() + ")", rows, 2);
                rows++;
            }
            jTextArea1.setText(r.getObservaciones());

        } catch (Exception e) {
        }
    }

    public void guardar() {
        crear_registro();
        if (estado == false) {
            limpiar();
        }
    }

    public void limpiar() {
        Component[] componentes = getComponents();
        for (Component componente : componentes) {
            String clase = componente.getClass().getName();
            if (clase.equals("javax.swing.JTextField")) {
                JTextField tmp = (JTextField) componente;
                tmp.setText("");
            }
            if (clase.equals("javax.swing.JComboBox")) {
                JComboBox tmp = (JComboBox) componente;
                tmp.removeAllItems();
            }
        }

        this.webDateField1.clear();
        this.jTextArea1.setText("");
        this.comboTipoRuta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"12 Horas", "16 Horas"}));
        this.jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Ruta", "Tipo", "Hora Llegada", "Ocupación Pasajeros"
                }
        ));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
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
                new String[]{
                    "Ruta", "# Recorridos Mañana", "Ocupación", "# Recorridos Tarde", "Ocupación", "# Recorridos Noche", "Ocupación"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Hora Inicio", "Hora Final", "Duración", "Km Inicial", "Km Final", "Total Km", "Actividad"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
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
                new String[]{
                    "Nombre y Apellido", "Registro", "Ruta"
                }
        ));

        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            jTable1.setValueAt(null, i, 1);

        }
        inicializar_combos();

    }

    private void crear_registro() throws HeadlessException {
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
            SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
            Date fechaReporteDiario = null;
            Date HoraInicio = null;
            Date HoraFin = null;
            try {
                fechaReporteDiario = df2.parse(webDateField1.getText().trim());

                HoraInicio = (Date) HInicio.getValue();
                HoraFin = (Date) spinner.getValue();

            } catch (Exception ex) {
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
            reporteDiario.setDiferenciaKm(Double.parseDouble(diferenciaKM.getText().trim()));
            reporteDiario.setTipoRuta(comboTipoRuta.getSelectedItem().toString());

            try {

                reporteDiario.setTanqueo(Long.parseLong(jTextField3.getText()));
            } catch (Exception e) {
                reporteDiario.setTanqueo(Long.parseLong(jTextField3.getText().replaceAll(",", ".")));
            }
            try {

                reporteDiario.setDuracion(Double.parseDouble(diferencia.getText()));
            } catch (Exception e) {
                reporteDiario.setDuracion(Double.parseDouble(diferencia.getText().replaceAll(",", ".")));
            }

            String mensaje = genericas.guardarBD(reporteDiario);
            if (mensaje.equals("")) {
                JOptionPane.showMessageDialog(null, "Se han guardado exitosamente los registros");

            } else {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error, por favor vuelve a intentarlo");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, por favor vuelve a intentarlo");
            System.out.println(e.toString());
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se puede agregar la hora de Inicio/Fin de la novedad");
                }
                novedad.setHoraInicio(fechaInicio);
                novedad.setHoraFinal(fechaFin);
                try {
                    novedad.setDuracion(Double.parseDouble(itera[2].toString()));
                    novedad.setTotalKm(Double.parseDouble(itera[5].toString()));
                } catch (Exception e) {
                    novedad.setDuracion(Double.parseDouble(itera[2].toString().replaceAll(",", ".")));
                    novedad.setTotalKm(Double.parseDouble(itera[5].toString().replaceAll(",", ".")));
                }

                novedad.setKmInicial(Integer.parseInt(itera[3].toString()));
                novedad.setKmFinal(Integer.parseInt(itera[4].toString()));

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
                ruta.setRecorridosMañana((itera[1] != null) ? Integer.parseInt(itera[1].toString()) : 0);
                ruta.setOcupacionMañana((itera[2] != null) ? Integer.parseInt(itera[2].toString()) : 0);
                ruta.setRecorridosTarde((itera[3] != null) ? Integer.parseInt(itera[3].toString()) : 0);
                ruta.setOcupacionTarde((itera[4] != null) ? Integer.parseInt(itera[4].toString()) : 0);
                ruta.setRecorridosNoche((itera[5] != null) ? Integer.parseInt(itera[5].toString()) : 0);
                ruta.setOcupacionNoche((itera[6] != null) ? Integer.parseInt(itera[6].toString()) : 0);
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
                } catch (Exception ex) {
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
        generar_Listener_Combo_horario_ruta();
        //        generar_Listener_Combo_Tipos();
    }

    public void generar_Listener_Combo_horario_ruta() {
        List<String> filterArray = new ArrayList<>();
        List<String> filterArrayConvertido = new ArrayList<>();
        filterArray = cls.getListadoHorariosRutasExternas(genericas.getIdComboLong(comboRutaExterna_I));
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm a");
        for (int x = 0; x < filterArray.size(); x++) {
            filterArrayConvertido.add(formato.format(filterArray.get(x)));
        }
        comboHoraLlegada_I.setModel(new DefaultComboBoxModel(filterArrayConvertido.toArray()));
    }

    public void generar_Listener_Combo_RutasE(final JTextField textfield, final JComboBox combo) {
        List<String> filterArray = new ArrayList<>();
        filterArray = cls.getListadoRutasTipos("Externa");
//        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        comboRutaExterna_I.setModel(new DefaultComboBoxModel(filterArray.toArray()));
//        javax.swing.table.TableColumn tc = this.jTable2.getColumnModel().getColumn(0);
//        TableCellEditor tce = new DefaultCellEditor(combo);
//        tc.setCellEditor(tce);
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
//        combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        comboActividadNovedad_I.setModel(new DefaultComboBoxModel(filterArray.toArray()));
//        javax.swing.table.TableColumn tc = this.jTable4.getColumnModel().getColumn(6);
//        TableCellEditor tce = new DefaultCellEditor(combo);
//        tc.setCellEditor(tce);
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoRutasTipos("Circular");
        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            combo.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        }
//            combo.showPopup();
//        } else {
////            combo.hidePopup();
//        }
    }

    public void filter_combo_Rutas(String enteredText, JComboBox combo) {

        List<String> filterArray = new ArrayList<>();
        try {
            filterArray = cls.getListadoRutas(enteredText, "Circular");
        } catch (Exception ex) {
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
        comboRutaExterna_I = new javax.swing.JComboBox<>();
        comboTipo_I = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboHoraLlegada_I = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtOcupacion_I = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        SpinnerDateModel sm1 = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner1 = new javax.swing.JSpinner(sm1);
        jLabel23 = new javax.swing.JLabel();
        SpinnerDateModel sms1 = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        HInicio1 = new javax.swing.JSpinner(sms1);
        jLabel22 = new javax.swing.JLabel();
        diferencia1 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        diferenciaKM1 = new javax.swing.JTextField();
        comboActividadNovedad_I = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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
        jLabel16 = new javax.swing.JLabel();
        diferenciaKM = new javax.swing.JTextField();
        comboTipoRuta = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Reporte Diario");

        webDateField1.setToolTipText("Fecha");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fecha");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Empleado");

        comboEmpleado.setEditable(true);
        comboEmpleado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

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

        jTextField1.setText("0");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTextField2.setText("0");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tanqueo # Galones");

        jTextField3.setToolTipText("Galones");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

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
                .addContainerGap(615, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ocupación Rutas Circulares", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ruta", "Tipo", "Hora Llegada", "Ocupación Pasajeros"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        comboRutaExterna_I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRutaExterna_IActionPerformed(evt);
            }
        });
        comboRutaExterna_I.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboRutaExterna_IPropertyChange(evt);
            }
        });

        comboTipo_I.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida" }));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Ruta");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Tipo");

        comboHoraLlegada_I.setEditable(true);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Hora Llegada");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Ocupación pasajeros");

        txtOcupacion_I.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOcupacion_IKeyTyped(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel18)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel19)
                                .addGap(164, 164, 164)
                                .addComponent(jLabel20)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel21))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboRutaExterna_I, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboTipo_I, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(comboHoraLlegada_I, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(txtOcupacion_I, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboRutaExterna_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTipo_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboHoraLlegada_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOcupacion_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ruta Ocupación", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora Inicio", "Hora Final", "Duración", "Km Inicial", "Km Final", "Total Km", "Actividad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
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

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Duración");

        JSpinner.DateEditor de1 = new JSpinner.DateEditor(spinner1, "hh:mm a");
        de1.getTextField().setEditable( true );
        spinner1.setEditor(de1);
        spinner1.addChangeListener(new MyFocusListener2());

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Hora Final");

        JSpinner.DateEditor des1 = new JSpinner.DateEditor(HInicio1, "hh:mm a");
        des1.getTextField().setEditable( true );
        HInicio1.setEditor(des1);
        HInicio1.addChangeListener(new MyFocusListener2());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Hora Inicio");

        diferencia1.setToolTipText("Hora ( HH:24)");
        diferencia1.setEnabled(false);

        jTextField5.setText("0");
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Km Inicial");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Total (km)");

        jTextField4.setText("0");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Km Final");

        diferenciaKM1.setToolTipText("Hora ( HH:24)");
        diferenciaKM1.setEnabled(false);

        comboActividadNovedad_I.setEditable(true);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Actividad Novedad");

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel22)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel23)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diferencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel24)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel27)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel28))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diferenciaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(comboActividadNovedad_I, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diferencia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diferenciaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboActividadNovedad_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)
                            .addComponent(jButton6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Observaciones", jPanel6);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        jButton1.setToolTipText("Guardar");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar.png"))); // NOI18N
        jButton2.setToolTipText("Limpiar");
        jButton2.setContentAreaFilled(false);
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

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Diferencia (km)");

        diferenciaKM.setToolTipText("Hora ( HH:24)");
        diferenciaKM.setEnabled(false);

        comboTipoRuta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12 Horas", "16 Horas" }));
        comboTipoRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoRutaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Tipo Ruta");

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
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(32, 32, 32)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(diferenciaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(HInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboTipoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel12)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel13)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(diferenciaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(HInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboTipoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (estado == false) {
            limpiar();
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboTipoRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoRutaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            DefaultTableModel dm = (DefaultTableModel) this.jTable2.getModel();
            dm.removeRow(jTable2.getSelectedRow());
            jTable2.setModel(dm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para eliminar");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        DefaultTableModel dm = (DefaultTableModel) this.jTable2.getModel();
        dm.addRow(new Object[]{comboRutaExterna_I.getSelectedItem().toString(), comboTipo_I.getSelectedItem().toString(), comboHoraLlegada_I.getSelectedItem().toString(), txtOcupacion_I.getText().trim()});
        jTable2.setModel(dm);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        Double valor;
        try {

            valor = Double.parseDouble(jTextField2.getText()) - Double.parseDouble(jTextField1.getText());
        } catch (Exception e) {
            valor = 0.0;
        }
        String expressn = valor.toString();

        diferenciaKM.setText(expressn);
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        Double valor;
        try {
            valor = Double.parseDouble(jTextField2.getText()) - Double.parseDouble(jTextField1.getText());
        } catch (Exception e) {
            valor = 0.0;
        }

        String expressn = valor.toString();
        diferenciaKM.setText(expressn);
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        Double valor;
        try {
            valor = Double.parseDouble(jTextField5.getText()) - Double.parseDouble(jTextField4.getText());
        } catch (Exception e) {
            valor = 0.0;
        }

        String expressn = valor.toString();
        diferenciaKM1.setText(expressn);
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        Double valor;
        try {
            valor = Double.parseDouble(jTextField5.getText()) - Double.parseDouble(jTextField4.getText());
        } catch (Exception e) {
            valor = 0.0;
        }

        String expressn = valor.toString();
        diferenciaKM1.setText(expressn);
    }//GEN-LAST:event_jTextField5FocusLost

    private void comboRutaExterna_IPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboRutaExterna_IPropertyChange

    }//GEN-LAST:event_comboRutaExterna_IPropertyChange

    private void comboRutaExterna_IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRutaExterna_IActionPerformed
        generar_Listener_Combo_horario_ruta();
    }//GEN-LAST:event_comboRutaExterna_IActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel dm = (DefaultTableModel) this.jTable4.getModel();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm a");
        try {

            dm.addRow(new Object[]{formato.format(HInicio1.getValue()), formato.format(spinner1.getValue()),
                Double.parseDouble(diferencia1.getText().replaceAll(",", ".")),
                jTextField4.getText(),
                jTextField5.getText(),
                Double.parseDouble(diferenciaKM1.getText().replaceAll(",", ".")),
                comboActividadNovedad_I.getSelectedItem()
            }
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible insertar la novedad, por favor, vuelva a intentarlo");
        }
        jTable4.setModel(dm);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            DefaultTableModel dm = (DefaultTableModel) this.jTable4.getModel();
            dm.removeRow(jTable4.getSelectedRow());
            jTable4.setModel(dm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para eliminar");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        validar(evt);

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        validar(evt);
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        validar(evt);
    }//GEN-LAST:event_jTextField3KeyTyped

    private void txtOcupacion_IKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacion_IKeyTyped
        validar(evt);
    }//GEN-LAST:event_txtOcupacion_IKeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
       validar(evt);
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
       validar(evt);
    }//GEN-LAST:event_jTextField5KeyTyped

    public void validar(java.awt.event.KeyEvent evt) {

        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter != '.')) {
            evt.consume(); // ignorar el evento de teclado
        }

    }

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

    private class MyFocusListener2 implements ChangeListener {

        public MyFocusListener2() {
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            String texto = calcularDiferenciaHoras((Date) HInicio1.getModel().getValue(), (Date) spinner1.getModel().getValue());
            diferencia1.setText(texto);
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
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class
        ));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner HInicio;
    private javax.swing.JSpinner HInicio1;
    private javax.swing.JComboBox comboActividad;
    private javax.swing.JComboBox<String> comboActividadNovedad_I;
    private javax.swing.JComboBox comboCircular;
    private javax.swing.JComboBox comboEmpleado;
    private javax.swing.JComboBox<String> comboHoraLlegada_I;
    private javax.swing.JComboBox comboPlaca;
    private javax.swing.JComboBox comboProyecto;
    private javax.swing.JComboBox<String> comboRutaExterna_I;
    private javax.swing.JComboBox<String> comboTipoRuta;
    private javax.swing.JComboBox<String> comboTipo_I;
    private javax.swing.JComboBox comboUsuario;
    private javax.swing.JTextField diferencia;
    private javax.swing.JTextField diferencia1;
    private javax.swing.JTextField diferenciaKM;
    private javax.swing.JTextField diferenciaKM1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JSpinner spinner;
    private javax.swing.JSpinner spinner1;
    private javax.swing.JTextField txtOcupacion_I;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables

}
