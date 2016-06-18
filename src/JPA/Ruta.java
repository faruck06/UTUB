/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "ruta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruta.findByTipos", query = "SELECT CONCAT(r.nombre, ' (' ,  r.idRuta , ')') FROM Ruta r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r"),
    @NamedQuery(name = "Ruta.findByIdRuta", query = "SELECT r FROM Ruta r WHERE r.idRuta = :idRuta"),
    @NamedQuery(name = "Ruta.findByNombre", query = "SELECT r FROM Ruta r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Ruta.findByNombres", query = "SELECT CONCAT(r.nombre, ' (' , r.idRuta , ')') FROM Ruta r WHERE upper(r.nombre) LIKE upper(:nombre) AND r.tipo = :tipo"),
    @NamedQuery(name = "Ruta.findByTipo", query = "SELECT r.nombre FROM Ruta r WHERE r.tipo = :tipo")})
public class Ruta implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta")
    private Long idRuta;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta")
    private Collection<RutaExterna> rutaExternaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta")
    private Collection<RutaOcupacion> rutaOcupacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta")
    private Collection<RutaHorario> rutaHorarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta")
    private Collection<ServicioIndividual> servicioIndividualCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRuta")
    private Collection<RutaExternaHorario> rutaExternaHorariosCollection;

    public Collection<RutaExternaHorario> getRutaExternaHorariosCollection() {
        return rutaExternaHorariosCollection;
    }

    public void setRutaExternaHorariosCollection(Collection<RutaExternaHorario> rutaExternaHorariosCollection) {
        this.rutaExternaHorariosCollection = rutaExternaHorariosCollection;
    }

    public Ruta() {
    }

    public Ruta(Long idRuta) {
        this.idRuta = idRuta;
    }

    public Ruta(Long idRuta, String nombre, String tipo) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Long idRuta) {
        Long oldIdRuta = this.idRuta;
        this.idRuta = idRuta;
        changeSupport.firePropertyChange("idRuta", oldIdRuta, idRuta);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    @XmlTransient
    public Collection<RutaExterna> getRutaExternaCollection() {
        return rutaExternaCollection;
    }

    public void setRutaExternaCollection(Collection<RutaExterna> rutaExternaCollection) {
        this.rutaExternaCollection = rutaExternaCollection;
    }

    @XmlTransient
    public Collection<RutaOcupacion> getRutaOcupacionCollection() {
        return rutaOcupacionCollection;
    }

    public void setRutaOcupacionCollection(Collection<RutaOcupacion> rutaOcupacionCollection) {
        this.rutaOcupacionCollection = rutaOcupacionCollection;
    }

    @XmlTransient
    public Collection<RutaHorario> getRutaHorarioCollection() {
        return rutaHorarioCollection;
    }

    public void setRutaHorarioCollection(Collection<RutaHorario> rutaHorarioCollection) {
        this.rutaHorarioCollection = rutaHorarioCollection;
    }

    @XmlTransient
    public Collection<ServicioIndividual> getServicioIndividualCollection() {
        return servicioIndividualCollection;
    }

    public void setServicioIndividualCollection(Collection<ServicioIndividual> servicioIndividualCollection) {
        this.servicioIndividualCollection = servicioIndividualCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRuta != null ? idRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.idRuta == null && other.idRuta != null) || (this.idRuta != null && !this.idRuta.equals(other.idRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.Ruta[ idRuta=" + idRuta + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
