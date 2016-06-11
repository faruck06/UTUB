/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "tipo_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t"),
    @NamedQuery(name = "TipoVehiculo.findByIdTipoVehiculo", query = "SELECT t FROM TipoVehiculo t WHERE t.idTipoVehiculo = :idTipoVehiculo"),
    @NamedQuery(name = "TipoVehiculo.findByNombre", query = "SELECT t FROM TipoVehiculo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoVehiculo.findByTipo", query = "SELECT t FROM TipoVehiculo t WHERE t.tipo = :tipo")})
public class TipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_vehiculo")
    private Long idTipoVehiculo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVehiculo")
    private Collection<Vehiculo> vehiculoCollection;

    public TipoVehiculo() {
    }

    public TipoVehiculo(Long idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public TipoVehiculo(Long idTipoVehiculo, String nombre) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.nombre = nombre;
    }

    public Long getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Long idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVehiculo)) {
            return false;
        }
        TipoVehiculo other = (TipoVehiculo) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.TipoVehiculo[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }
    
}
