/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "ruta_externa_horario", catalog = "sql5122970", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaExternaHorario.findAll", query = "SELECT r FROM RutaExternaHorario r"),
    @NamedQuery(name = "RutaExternaHorario.findByIdRutaExternaHorario", query = "SELECT r FROM RutaExternaHorario r WHERE r.idRutaExternaHorario = :idRutaExternaHorario"),
    @NamedQuery(name = "RutaExternaHorario.findByRutaExterna", query = "SELECT r.horaLlegada FROM RutaExternaHorario r WHERE r.idRuta.idRuta = :idRuta")})
public class RutaExternaHorario implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_ruta_externa_horario")
    private Long idRutaExternaHorario;
    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Column(name = "hora_Salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @JoinColumn(name = "id_ruta_externa", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta idRuta;

    public RutaExternaHorario() {
    }

    public RutaExternaHorario(Long idRutaExternaHorario) {
        this.idRutaExternaHorario = idRutaExternaHorario;
    }

    public Long getIdRutaExternaHorario() {
        return idRutaExternaHorario;
    }

    public void setIdRutaExternaHorario(Long idRutaExternaHorario) {
        Long oldIdRutaExternaHorario = this.idRutaExternaHorario;
        this.idRutaExternaHorario = idRutaExternaHorario;
        changeSupport.firePropertyChange("idRutaExternaHorario", oldIdRutaExternaHorario, idRutaExternaHorario);
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        Ruta oldIdRuta = this.idRuta;
        this.idRuta = idRuta;
        changeSupport.firePropertyChange("idRuta", oldIdRuta, idRuta);
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutaExternaHorario != null ? idRutaExternaHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaExternaHorario)) {
            return false;
        }
        RutaExternaHorario other = (RutaExternaHorario) object;
        if ((this.idRutaExternaHorario == null && other.idRutaExternaHorario != null) || (this.idRutaExternaHorario != null && !this.idRutaExternaHorario.equals(other.idRutaExternaHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.RutaExternaHorario[ idRutaExternaHorario=" + idRutaExternaHorario + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
