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
@Table(name = "ruta_ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaOcupacion.findAll", query = "SELECT r FROM RutaOcupacion r"),
    @NamedQuery(name = "RutaOcupacion.findByIdRutaOcupacion", query = "SELECT r FROM RutaOcupacion r WHERE r.idRutaOcupacion = :idRutaOcupacion"),
    @NamedQuery(name = "RutaOcupacion.findByRecorridosMa\u00f1ana", query = "SELECT r FROM RutaOcupacion r WHERE r.recorridosMa\u00f1ana = :recorridosMa\u00f1ana"),
    @NamedQuery(name = "RutaOcupacion.findByOcupacionMa\u00f1ana", query = "SELECT r FROM RutaOcupacion r WHERE r.ocupacionMa\u00f1ana = :ocupacionMa\u00f1ana"),
    @NamedQuery(name = "RutaOcupacion.findByRecorridosTarde", query = "SELECT r FROM RutaOcupacion r WHERE r.recorridosTarde = :recorridosTarde"),
    @NamedQuery(name = "RutaOcupacion.findByOcupacionTarde", query = "SELECT r FROM RutaOcupacion r WHERE r.ocupacionTarde = :ocupacionTarde"),
    @NamedQuery(name = "RutaOcupacion.findByRecorridosNoche", query = "SELECT r FROM RutaOcupacion r WHERE r.recorridosNoche = :recorridosNoche"),
    @NamedQuery(name = "RutaOcupacion.findByOcupacionNoche", query = "SELECT r FROM RutaOcupacion r WHERE r.ocupacionNoche = :ocupacionNoche")})
public class RutaOcupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta_ocupacion")
    private Long idRutaOcupacion;
    @Column(name = "recorridos_ma\u00f1ana")
    private Long recorridosMañana;
    @Column(name = "ocupacion_ma\u00f1ana")
    private Integer ocupacionMañana;
    @Column(name = "recorridos_tarde")
    private Long recorridosTarde;
    @Column(name = "ocupacion_tarde")
    private Integer ocupacionTarde;
    @Column(name = "recorridos_noche")
    private Long recorridosNoche;
    @Column(name = "ocupacion_noche")
    private Integer ocupacionNoche;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta idRuta;
    @JoinColumn(name = "id_reporte_diario", referencedColumnName = "id_reporte_diario")
    @ManyToOne(optional = false)
    private ReporteDiario idReporteDiario;

    public RutaOcupacion() {
    }

    public RutaOcupacion(Long idRutaOcupacion) {
        this.idRutaOcupacion = idRutaOcupacion;
    }

    public Long getIdRutaOcupacion() {
        return idRutaOcupacion;
    }

    public void setIdRutaOcupacion(Long idRutaOcupacion) {
        this.idRutaOcupacion = idRutaOcupacion;
    }

    public Long getRecorridosMañana() {
        return recorridosMañana;
    }

    public void setRecorridosMañana(Long recorridosMañana) {
        this.recorridosMañana = recorridosMañana;
    }

    public Integer getOcupacionMañana() {
        return ocupacionMañana;
    }

    public void setOcupacionMañana(Integer ocupacionMañana) {
        this.ocupacionMañana = ocupacionMañana;
    }

    public Long getRecorridosTarde() {
        return recorridosTarde;
    }

    public void setRecorridosTarde(Long recorridosTarde) {
        this.recorridosTarde = recorridosTarde;
    }

    public Integer getOcupacionTarde() {
        return ocupacionTarde;
    }

    public void setOcupacionTarde(Integer ocupacionTarde) {
        this.ocupacionTarde = ocupacionTarde;
    }

    public Long getRecorridosNoche() {
        return recorridosNoche;
    }

    public void setRecorridosNoche(Long recorridosNoche) {
        this.recorridosNoche = recorridosNoche;
    }

    public Integer getOcupacionNoche() {
        return ocupacionNoche;
    }

    public void setOcupacionNoche(Integer ocupacionNoche) {
        this.ocupacionNoche = ocupacionNoche;
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
        hash += (idRutaOcupacion != null ? idRutaOcupacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaOcupacion)) {
            return false;
        }
        RutaOcupacion other = (RutaOcupacion) object;
        if ((this.idRutaOcupacion == null && other.idRutaOcupacion != null) || (this.idRutaOcupacion != null && !this.idRutaOcupacion.equals(other.idRutaOcupacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.RutaOcupacion[ idRutaOcupacion=" + idRutaOcupacion + " ]";
    }
    
}
