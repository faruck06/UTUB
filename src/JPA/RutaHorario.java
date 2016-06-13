/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "ruta_horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaHorario.findAll", query = "SELECT r FROM RutaHorario r"),
    @NamedQuery(name = "RutaHorario.findByIdRutaHorario", query = "SELECT r FROM RutaHorario r WHERE r.idRutaHorario = :idRutaHorario"),
    @NamedQuery(name = "RutaHorario.findByOcupacion", query = "SELECT r FROM RutaHorario r WHERE r.ocupacion = :ocupacion")})
public class RutaHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta_horario")
    private Long idRutaHorario;
    @Basic(optional = false)
    @Column(name = "ocupacion")
    private short ocupacion;
    @JoinColumn(name = "id_reporte_diario", referencedColumnName = "id_reporte_diario")
    @ManyToOne(optional = false)
    private ReporteDiario idReporteDiario;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta idRuta;
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne(optional = false)
    private Horario idHorario;

    public RutaHorario() {
    }

    public RutaHorario(short ocupacion, ReporteDiario idReporteDiario, Ruta idRuta, Horario idHorario) {
        this.ocupacion = ocupacion;
        this.idReporteDiario = idReporteDiario;
        this.idRuta = idRuta;
        this.idHorario = idHorario;
    }

    public RutaHorario(Long idRutaHorario) {
        this.idRutaHorario = idRutaHorario;
    }

    public RutaHorario(Long idRutaHorario, short ocupacion) {
        this.idRutaHorario = idRutaHorario;
        this.ocupacion = ocupacion;
    }

    public Long getIdRutaHorario() {
        return idRutaHorario;
    }

    public void setIdRutaHorario(Long idRutaHorario) {
        this.idRutaHorario = idRutaHorario;
    }

    public short getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(short ocupacion) {
        this.ocupacion = ocupacion;
    }

    public ReporteDiario getIdReporteDiario() {
        return idReporteDiario;
    }

    public void setIdReporteDiario(ReporteDiario idReporteDiario) {
        this.idReporteDiario = idReporteDiario;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutaHorario != null ? idRutaHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaHorario)) {
            return false;
        }
        RutaHorario other = (RutaHorario) object;
        if ((this.idRutaHorario == null && other.idRutaHorario != null) || (this.idRutaHorario != null && !this.idRutaHorario.equals(other.idRutaHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.RutaHorario[ idRutaHorario=" + idRutaHorario + " ]";
    }

}
