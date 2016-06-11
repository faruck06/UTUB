/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Faruck
 */
public class test {

    public static void main(String[] args) {
        try {
            ficha_tecnica("zx");
        }
        catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5122970", "sql5122970", "xwqNy5xu8Z");
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            return rs;
        }
        catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void ficha_tecnica(String placa) throws IOException {
        try {
            String sql = " SELECT placa, 1 tipo_vehiculo, duracion from `reporte_diario` ";
//            sql += "'" + placa + "'";
            ResultSet rs = getData(sql);
            final String ruta = "/src/view/Reportes/Facturacion_1.jrxml";
//            HashMap map = new HashMap();
//            map.put("idPlaca", placa);
            JasperReport jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath() + ruta);
            JRResultSetDataSource jasperReports = new JRResultSetDataSource(rs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jasperReports);
            //Report saved in specified path
            String nombre = "D:\\reporte.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, nombre);
            //Report open in Runtime
            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + nombre);
        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }
}
