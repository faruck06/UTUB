/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import JPA.ReporteDiario;
import JPA.UsuarioProyecto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
     *
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

    public List<String> getListadoActividadNovedad() {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("ActividadNovedad.findAll");
        return query.getResultList();
    }

    public List<String> getListadoUsuariosProyecto(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("UsuarioProyecto.findByNombres");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }

    /**
     * Trae el listado de rutas sin filtrar, solo por nombre
     *
     * @param nombreRuta
     * @return
     */
    public List<String> getListadoRutas(String nombreRuta, String tipo) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Ruta.findByNombres");
        query.setParameter("nombre", nombreRuta + "%");
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }

    public List<String> getListadoRutasTipos(String tipoRuta) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Ruta.findByTipos");
        query.setParameter("tipo", tipoRuta);
        return query.getResultList();
    }

    public List<String> getListadoHorarios() {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Horario.findAll");
        return query.getResultList();
    }

    /**
     * Obtiene el Id de la ruta horario
     *
     * @param horario
     * @return
     */
    public Long getIdRutaHorario(String horario) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Horario.findByHoraInicios");
        query.setParameter("horaInicio", getDate_toDate(horario));
        Long dato = (Long) query.getResultList().get(0);
        return dato;
    }

    public Date getDate_toDate(String fecha) {

        DateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date startDate = null;
        try {
            startDate = formatter.parse(fecha);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public List<ReporteDiario> get_Listado_Reportes(ReporteDiario reporteDiario) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();

        String sql = "SELECT r FROM ReporteDiario r WHERE 1=1 ";
        if (reporteDiario.getIdEmpleado() != null) {
            sql += "AND r.idEmpleado = :idEmpleado ";
        }
        if (reporteDiario.getPlaca() != null) {
            sql += "AND (r.placa = :placa) ";
        }
        if (reporteDiario.getFecha() != null) {
            sql += "AND (r.fecha = :fecha) ";
        }
        if (reporteDiario.getIdProyecto() != null) {
            sql += "AND r.idProyecto=:idProyecto ";
        }
        if (reporteDiario.getIdActividadPrincipal() != null) {
            sql += "AND r.idActividadPrincipal=:idActividadPrincipal ";
        }
        if (reporteDiario.getIdUsuarioProyecto() != null) {
            sql += "AND r.idUsuarioProyecto=:idUsuarioProyecto ";
        }

        Query query = em.createQuery(sql);
        if (reporteDiario.getIdEmpleado() != null) {
            query.setParameter("idEmpleado", reporteDiario.getIdEmpleado());
        }
        if (reporteDiario.getPlaca() != null) {
            query.setParameter("placa", reporteDiario.getPlaca());
        }

        if (reporteDiario.getIdUsuarioProyecto() != null) {
            query.setParameter("idUsuarioProyecto", reporteDiario.getIdUsuarioProyecto());
        }

        if (reporteDiario.getFecha() != null) {
            query.setParameter("fecha", reporteDiario.getFecha());
        }

        if (reporteDiario.getIdActividadPrincipal() != null) {
            query.setParameter("idActividadPrincipal", reporteDiario.getIdActividadPrincipal());
        }

        if (reporteDiario.getIdProyecto() != null) {
            query.setParameter("idProyecto", reporteDiario.getIdProyecto());
        }

        return query.getResultList();
    }

    public ReporteDiario getReporteDiarioEntidad(Object objeto) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("ReporteDiario.findByIdReporteDiario");
        query.setParameter("idReporteDiario", Long.parseLong(objeto.toString()));
        return (ReporteDiario) query.getSingleResult();
    }

    public List<String> getListadoHorariosRutasExternas(Long idRuta) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("RutaExternaHorario.findByRutaExterna");
        query.setParameter("idRuta", idRuta);
        return query.getResultList();
    }

    public List<String> getListadoUsuarios(String usuario) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Usuario.findByAllUsuarios");
        query.setParameter("usuario", usuario + "%");
        return query.getResultList();
    }

    public List<String> getListadoRol(String nombre) {
        Genericas gen = new Genericas();
        EntityManager em = gen.getEntity();
        Query query = em.createNamedQuery("Rol.findByAllRol");
        query.setParameter("nombre", nombre + "%");
        return query.getResultList();
    }
}
