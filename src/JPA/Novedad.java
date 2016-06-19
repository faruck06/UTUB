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
@Table(name = "novedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novedad.findAll", query = "SELECT n FROM Novedad n"),
    @NamedQuery(name = "Novedad.findByIdNovedad", query = "SELECT n FROM Novedad n WHERE n.idNovedad = :idNovedad"),
    @NamedQuery(name = "Novedad.findByHoraInicio", query = "SELECT n FROM Novedad n WHERE n.horaInicio = :horaInicio"),
    @NamedQuery(name = "Novedad.findByHoraFinal", query = "SELECT n FROM Novedad n WHERE n.horaFinal = :horaFinal"),
    @NamedQuery(name = "Novedad.findByDuracion", query = "SELECT n FROM Novedad n WHERE n.duracion = :duracion"),
    @NamedQuery(name = "Novedad.findByKmInicial", query = "SELECT n FROM Novedad n WHERE n.kmInicial = :kmInicial"),
    @NamedQuery(name = "Novedad.findByKmFinal", query = "SELECT n FROM Novedad n WHERE n.kmFinal = :kmFinal"),
    @NamedQuery(name = "Novedad.findByTotalKm", query = "SELECT n FROM Novedad n WHERE n.totalKm = :totalKm")})
public class Novedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad")
    private Long idNovedad;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_final")
    @Temporal(TemporalType.TIME)
    private Date horaFinal;
    @Column(name = "duracion")
    private Double duracion;
    @Column(name = "km_inicial")
    private Integer kmInicial;
    @Column(name = "km_final")
    private Integer kmFinal;
    @Column(name = "total_km")
    private Double totalKm;
    @JoinColumn(name = "id_novedad_actividad", referencedColumnName = "id_actividad_novedad")
    @ManyToOne(optional = false)
    private ActividadNovedad idNovedadActividad;
    @JoinColumn(name = "id_reporte_diario", referencedColumnName = "id_reporte_diario")
    @ManyToOne(optional = false)
    private ReporteDiario idReporteDiario;

    public Novedad() {
    }

    public Novedad(Long idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Long getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Long idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Integer getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Integer getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Double totalKm) {
        this.totalKm = totalKm;
    }

    public ActividadNovedad getIdNovedadActividad() {
        return idNovedadActividad;
    }

    public void setIdNovedadActividad(ActividadNovedad idNovedadActividad) {
        this.idNovedadActividad = idNovedadActividad;
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
        hash += (idNovedad != null ? idNovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novedad)) {
            return false;
        }
        Novedad other = (Novedad) object;
        if ((this.idNovedad == null && other.idNovedad != null) || (this.idNovedad != null && !this.idNovedad.equals(other.idNovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.Novedad[ idNovedad=" + idNovedad + " ]";
    }

}
