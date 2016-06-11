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
@Table(name = "actividad_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadPrincipal.findByNombres", query = "SELECT CONCAT(a.nombre,' (',a.idActividadPrincipal,')')  FROM ActividadPrincipal a WHERE upper(a.nombre) LIKE upper(:nombre)"),
    @NamedQuery(name = "ActividadPrincipal.findAll", query = "SELECT a FROM ActividadPrincipal a"),
    @NamedQuery(name = "ActividadPrincipal.findByIdActividadPrincipal", query = "SELECT a FROM ActividadPrincipal a WHERE a.idActividadPrincipal = :idActividadPrincipal"),
    @NamedQuery(name = "ActividadPrincipal.findByNombre", query = "SELECT a FROM ActividadPrincipal a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "ActividadPrincipal.findByTipo", query = "SELECT a FROM ActividadPrincipal a WHERE a.tipo = :tipo")})
public class ActividadPrincipal implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad_principal")
    private Long idActividadPrincipal;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividadPrincipal")
    private Collection<ReporteDiario> reporteDiarioCollection;

    public ActividadPrincipal() {
    }

    public ActividadPrincipal(Long idActividadPrincipal) {
        this.idActividadPrincipal = idActividadPrincipal;
    }

    public ActividadPrincipal(Long idActividadPrincipal, String nombre) {
        this.idActividadPrincipal = idActividadPrincipal;
        this.nombre = nombre;
    }

    public Long getIdActividadPrincipal() {
        return idActividadPrincipal;
    }

    public void setIdActividadPrincipal(Long idActividadPrincipal) {
        Long oldIdActividadPrincipal = this.idActividadPrincipal;
        this.idActividadPrincipal = idActividadPrincipal;
        changeSupport.firePropertyChange("idActividadPrincipal", oldIdActividadPrincipal, idActividadPrincipal);
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
    public Collection<ReporteDiario> getReporteDiarioCollection() {
        return reporteDiarioCollection;
    }

    public void setReporteDiarioCollection(Collection<ReporteDiario> reporteDiarioCollection) {
        this.reporteDiarioCollection = reporteDiarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadPrincipal != null ? idActividadPrincipal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadPrincipal)) {
            return false;
        }
        ActividadPrincipal other = (ActividadPrincipal) object;
        if ((this.idActividadPrincipal == null && other.idActividadPrincipal != null) || (this.idActividadPrincipal != null && !this.idActividadPrincipal.equals(other.idActividadPrincipal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.ActividadPrincipal[ idActividadPrincipal=" + idActividadPrincipal + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
