/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.persistence.EntityManager;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 *
 * @author Developer
 */
public class Genericas {

    public EntityManager getEntity() {
        return createEntityManagerFactory(cadena_conexion).createEntityManager();
    }
    public static final String cadena_conexion = "UTUBPU";
}
