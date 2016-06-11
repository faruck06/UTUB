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
@Table(name = "servicio_individual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioIndividual.findAll", query = "SELECT s FROM ServicioIndividual s"),
    @NamedQuery(name = "ServicioIndividual.findByIdServicioIndivudual", query = "SELECT s FROM ServicioIndividual s WHERE s.idServicioIndivudual = :idServicioIndivudual"),
    @NamedQuery(name = "ServicioIndividual.findByNombres", query = "SELECT s FROM ServicioIndividual s WHERE s.nombres = :nombres"),
    @NamedQuery(name = "ServicioIndividual.findByRegistro", query = "SELECT s FROM ServicioIndividual s WHERE s.registro = :registro")})
public class ServicioIndividual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio_indivudual")
    private Long idServicioIndivudual;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "registro")
    private String registro;
    @JoinColumn(name = "id_reporte_diario", referencedColumnName = "id_reporte_diario")
    @ManyToOne(optional = false)
    private ReporteDiario idReporteDiario;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta idRuta;

    public ServicioIndividual() {
    }

    public ServicioIndividual(Long idServicioIndivudual) {
        this.idServicioIndivudual = idServicioIndivudual;
    }

    public Long getIdServicioIndivudual() {
        return idServicioIndivudual;
    }

    public void setIdServicioIndivudual(Long idServicioIndivudual) {
        this.idServicioIndivudual = idServicioIndivudual;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicioIndivudual != null ? idServicioIndivudual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioIndividual)) {
            return false;
        }
        ServicioIndividual other = (ServicioIndividual) object;
        if ((this.idServicioIndivudual == null && other.idServicioIndivudual != null) || (this.idServicioIndivudual != null && !this.idServicioIndivudual.equals(other.idServicioIndivudual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.ServicioIndividual[ idServicioIndivudual=" + idServicioIndivudual + " ]";
    }
    
}
