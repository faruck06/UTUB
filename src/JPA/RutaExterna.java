/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "ruta_externa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaExterna.findAll", query = "SELECT r FROM RutaExterna r"),
    @NamedQuery(name = "RutaExterna.findByIdRutaExterna", query = "SELECT r FROM RutaExterna r WHERE r.idRutaExterna = :idRutaExterna"),
    @NamedQuery(name = "RutaExterna.findByIngreso", query = "SELECT r FROM RutaExterna r WHERE r.ingreso = :ingreso"),
    @NamedQuery(name = "RutaExterna.findBySalida", query = "SELECT r FROM RutaExterna r WHERE r.salida = :salida"),
    @NamedQuery(name = "RutaExterna.findByHoraLlegada", query = "SELECT r FROM RutaExterna r WHERE r.horaLlegada = :horaLlegada"),
    @NamedQuery(name = "RutaExterna.findByOcupacion", query = "SELECT r FROM RutaExterna r WHERE r.ocupacion = :ocupacion")})
public class RutaExterna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta_externa")
    private Long idRutaExterna;
    @Column(name = "ingreso")
    private String ingreso;
    @Column(name = "salida")
    private String salida;
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Column(name = "ocupacion")
    private Short ocupacion;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta idRuta;
    @JoinColumn(name = "id_reporte_diario", referencedColumnName = "id_reporte_diario")
    @ManyToOne(optional = false)
    private ReporteDiario idReporteDiario;

    public RutaExterna() {
    }

    public RutaExterna(Long idRutaExterna) {
        this.idRutaExterna = idRutaExterna;
    }

    public Long getIdRutaExterna() {
        return idRutaExterna;
    }

    public void setIdRutaExterna(Long idRutaExterna) {
        this.idRutaExterna = idRutaExterna;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Short getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Short ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
    }

    public ReporteDiario getIdReporteDiario() {
        return idReporteDiario;
    }

    public void setIdReporteDiario(ReporteDiario idReporteDiario) {
        this.idReporteDiario = idReporteDiario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutaExterna != null ? idRutaExterna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaExterna)) {
            return false;
        }
        RutaExterna other = (RutaExterna) object;
        if ((this.idRutaExterna == null && other.idRutaExterna != null) || (this.idRutaExterna != null && !this.idRutaExterna.equals(other.idRutaExterna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.RutaExterna[ idRutaExterna=" + idRutaExterna + " ]";
    }

}
