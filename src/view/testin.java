/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author Faruck
 */
public class testin {

    private static ResultSet data = null;

    public static void main(String args[]) {
        Random random = new Random();
        int value = random.nextInt(50);

        System.out.println("MySQL CRUD Example.");
        String url = "jdbc:mysql://192.168.0.87:3306/";
        String dbName = "sql5122970";
        String userName = "sql5122970";
        String password = "xwqNy5xu8Z";
        Connection db = null;

        try {
            System.out.println("Connected to the DB");
            db = DriverManager.getConnection(url + dbName, userName, password);
            Statement query = (Statement) db.createStatement();
//            query.executeUpdate("INSERT INTO javaTable(firstName, lastName) VALUES ('java" + value + "', 'jones')");
//
            data = query.executeQuery("select * from ruta");

            db.close();
            System.out.println("closed connection");
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }
}
