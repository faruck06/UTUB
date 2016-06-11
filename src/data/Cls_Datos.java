/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import JPA.UsuarioProyecto;
import src.Genericas;

/**
 *
 * @author Developer
 */
public class Cls_Datos {

    public List<UsuarioProyecto> getUsuarioProyecto(String nombre, String tipo) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("UsuarioProyecto.findByAll");
        query.setParameter("tipo", tipo);
        query.setParameter("nombre", nombre);
        return query.getResultList();

    }

    /**
     * Metodo que sirve para llenar el combo de empleados
     */
    public List<String> getListadoEmpleados(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Empleado.findByAllNombres");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }

    /**
     * Metodo que sirve para llenar el combo de vehiculos
     */
    public List<String> getListadoVehiculos(String placa) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Vehiculo.findByPlacas");
        query.setParameter("placa", placa + "%");
        return query.getResultList();
    }

    /**
     * Metodo que sirve para llenar el combo de proyectos
     */
    public List<String> getListadoProyectos(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Proyecto.findByNombres");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }

    /**
     * Metodo que sirve para llenar el combo de proyectos
     * @param nombre
     * @return 
     */
    public List<String> getListadoActividades(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("ActividadPrincipal.findByNombres");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }

    public List<String> getListadoUsuariosProyecto(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("UsuarioProyecto.findByNombres");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }

    /**Trae el listado de rutas sin filtrar, solo por nombre
     * @param nombreRuta
     * @return 
     */
    public List<String> getListadoRutas(String nombreRuta) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Ruta.findByNombres");
        query.setParameter("nombre", nombreRuta + "%");
        return query.getResultList();
    }
}
