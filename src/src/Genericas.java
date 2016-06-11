/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.persistence.EntityManager;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.swing.JComboBox;

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
            entityManager.persist(objeto);
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

    public static final String cadena_conexion = "UTUBPU";
}
