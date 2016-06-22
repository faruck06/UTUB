/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import JPA.ReporteDiario;
import java.io.File;
import java.util.Map;
import javax.persistence.EntityManager;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.swing.JComboBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 *
 * @author Developer
 */
public class Genericas {

    /**
     * Metodo que retorna la entidad creada con la cadena de conexion del
     * proyecto
     */
    public EntityManager getEntity() {
        return createEntityManagerFactory(cadena_conexion).createEntityManager();
    }

    /**
     * Metodo que almacena un objeto de tipo entidad en la base de datos
     */
    public String guardarBD(Object objeto) {
        try {
            javax.persistence.EntityManager entityManager = createEntityManagerFactory(cadena_conexion).createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "";
        }
        catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * Metodo que elimina un objeto de tipo entidad en la base de datos
     */
    public String eliminarReporteDiario(Long id) {
        try {
            javax.persistence.EntityManager entityManager = createEntityManagerFactory(cadena_conexion).createEntityManager();
            ReporteDiario reporte = entityManager.find(ReporteDiario.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(reporte);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "";
        }
        catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * Metodo que retorna el valor (String) del combo seleccionado Representado
     * por el valor entre paréntesis en el item seleccionado del combo
     */
    public String getIdCombo(JComboBox combo) {
        String texto = combo.getSelectedItem().toString();
        String cadena;
        cadena = texto.substring(texto.indexOf("(") + 1, texto.indexOf(")"));
        if (cadena.equals("")) {
            return "";
        } else {
            return cadena;
        }
    }

    /**
     * Metodo que retorna el valor (String) del combo seleccionado Representado
     * por el valor entre paréntesis en el item seleccionado del combo
     */
    public Long getIdComboLong(JComboBox combo) {
        String texto = combo.getSelectedItem().toString();
        String cadena;
        Long id;
        cadena = texto.substring(texto.indexOf("(") + 1, texto.indexOf(")"));
        try {
            id = Long.parseLong(cadena);
        }
        catch (Exception e) {
            id = Long.parseLong("0");
        }
        return id;
    }

    /**
     * Metodo que retorna el valor (String) del combo seleccionado Representado
     * por el valor que NO ESTÁ !! entre paréntesis en el item seleccionado del
     * combo Retorna la cadena con trim para eliminar los posibles espacios en
     * blanco al final de la palabra presentada en pantalla
     *
     */
    public String getTextoCombo(JComboBox combo) {
        String texto = combo.getSelectedItem().toString();
        String cadena;
        cadena = texto.substring(0, texto.indexOf("("));
        if (cadena.equals("")) {
            return "";
        } else {
            return cadena.trim();
        }
    }

    public Long getIdFromText(String texto) {
        String cadena = texto.substring(texto.indexOf("(") + 1, texto.indexOf(")"));
        return Long.parseLong(cadena);
    }

    public void generar_pdf(Map<String, Object> parameters, String nombreReporte, String rutaSalidaReporte, String nombreReportePDF) throws JRException {
        // Compile jrxml file.
        JasperReport jasperReport = JasperCompileManager
                .compileReport(rutaReportes + nombreReporte);

        Genericas genericas = new Genericas();
        EntityManager entityManager = genericas.getEntity();
        entityManager.getTransaction().begin();
        java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
        entityManager.getTransaction().commit();

        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, connection);

        // Make sure the output directory exists.
        File outDir = new File(rutaSalidaReporte);
        outDir.mkdirs();

        // Export to PDF.
        JasperExportManager.exportReportToPdfFile(print,
                rutaSalidaReporte + nombreReportePDF);

    }

    public void generar_excel(Map<String, Object> parameters, String nombreReporte, String rutaSalidaReporte, String nombreReportePDF) throws JRException {
        // Compile jrxml file.
        JasperReport jasperReport = JasperCompileManager
                .compileReport(rutaReportes + nombreReporte);

        Genericas genericas = new Genericas();
        EntityManager entityManager = genericas.getEntity();
        entityManager.getTransaction().begin();
        java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
        entityManager.getTransaction().commit();

        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, connection);

        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setExporterInput(new SimpleExporterInput(print));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(rutaSalidaReporte + nombreReportePDF));
        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(false);
        xlsReportConfiguration.setWhitePageBackground(false);
        xlsExporter.setConfiguration(xlsReportConfiguration);

        xlsExporter.exportReport();
//
        entityManager.close();
    }

    private static final String rutaReportes = "src/view/Reportes/";

    public static final String cadena_conexion = "UTUBPU";
}
