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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "reporte_diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteDiario.getAllReporteDiario", query = "SELECT r FROM ReporteDiario r WHERE r.idEmpleado = :idEmpleado OR (r.placa = :placa) OR (r.idProyecto=:idProyecto) OR (r.idActividadPrincipal=:idActividadPrincipal) OR (r.idUsuarioProyecto=:idUsuarioProyecto) OR ((r.placa = :placa) AND (r.idActividadPrincipal = :idActividadPrincipal) AND (r.idUsuarioProyecto = :idUsuarioProyecto) AND (r.idProyecto = :idProyecto))"),
    @NamedQuery(name = "ReporteDiario.getConsultaReporte1", query = "SELECT r.placa, r.placa.idTipoVehiculo.nombre , r.duracion FROM ReporteDiario r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReporteDiario.findAll", query = "SELECT r FROM ReporteDiario r"),
    @NamedQuery(name = "ReporteDiario.findByIdReporteDiario", query = "SELECT r FROM ReporteDiario r WHERE r.idReporteDiario = :idReporteDiario"),
    @NamedQuery(name = "ReporteDiario.findByFecha", query = "SELECT r FROM ReporteDiario r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReporteDiario.findByHoraInicio", query = "SELECT r FROM ReporteDiario r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "ReporteDiario.findByHoraFin", query = "SELECT r FROM ReporteDiario r WHERE r.horaFin = :horaFin"),
    @NamedQuery(name = "ReporteDiario.findByKmInicial", query = "SELECT r FROM ReporteDiario r WHERE r.kmInicial = :kmInicial"),
    @NamedQuery(name = "ReporteDiario.findByKmFinal", query = "SELECT r FROM ReporteDiario r WHERE r.kmFinal = :kmFinal"),
    @NamedQuery(name = "ReporteDiario.findByTanqueo", query = "SELECT r FROM ReporteDiario r WHERE r.tanqueo = :tanqueo"),
    @NamedQuery(name = "ReporteDiario.findByObservaciones", query = "SELECT r FROM ReporteDiario r WHERE r.observaciones = :observaciones")})
public class ReporteDiario implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporte_diario")
    private Long idReporteDiario;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Column(name = "duracion")
    private Double duracion;
    @Column(name = "km_inicial")
    private Double kmInicial;
    @Column(name = "km_final")
    private Double kmFinal;
    @Column(name = "diferencia_km")
    private Double diferenciaKm;
    @Column(name = "tanqueo")
    private Long tanqueo;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "tipo_ruta_horas")
    private String tipoRuta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteDiario")
    private Collection<RutaExterna> rutaExternaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteDiario")
    private Collection<RutaOcupacion> rutaOcupacionCollection;
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Vehiculo placa;
    @JoinColumn(name = "id_actividad_principal", referencedColumnName = "id_actividad_principal")
    @ManyToOne(optional = false)
    private ActividadPrincipal idActividadPrincipal;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @JoinColumn(name = "id_usuario_proyecto", referencedColumnName = "id_usuario_proyecto")
    @ManyToOne(optional = false)
    private UsuarioProyecto idUsuarioProyecto;
    @JoinColumn(name = "id_empleado", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteDiario")
    private Collection<Novedad> novedadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteDiario")
    private Collection<RutaHorario> rutaHorarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteDiario")
    private Collection<ServicioIndividual> servicioIndividualCollection;

    public ReporteDiario() {
    }

    public ReporteDiario(Long idReporteDiario) {
        this.idReporteDiario = idReporteDiario;
    }

    public Long getIdReporteDiario() {
        return idReporteDiario;
    }

    public void setIdReporteDiario(Long idReporteDiario) {
        Long oldIdReporteDiario = this.idReporteDiario;
        this.idReporteDiario = idReporteDiario;
        changeSupport.firePropertyChange("idReporteDiario", oldIdReporteDiario, idReporteDiario);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        Date oldHoraInicio = this.horaInicio;
        this.horaInicio = horaInicio;
        changeSupport.firePropertyChange("horaInicio", oldHoraInicio, horaInicio);
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        Date oldHoraFin = this.horaFin;
        this.horaFin = horaFin;
        changeSupport.firePropertyChange("horaFin", oldHoraFin, horaFin);
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        Double oldDuracion = this.duracion;
        this.duracion = duracion;
        changeSupport.firePropertyChange("duracion", oldDuracion, duracion);
    }

    public Double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Double kmInicial) {
        Double oldKmInicial = this.kmInicial;
        this.kmInicial = kmInicial;
        changeSupport.firePropertyChange("kmInicial", oldKmInicial, kmInicial);
    }

    public Double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Double kmFinal) {
        Double oldKmFinal = this.kmFinal;
        this.kmFinal = kmFinal;
        changeSupport.firePropertyChange("kmFinal", oldKmFinal, kmFinal);
    }

    public Double getDiferenciaKm() {
        return diferenciaKm;
    }

    public void setDiferenciaKm(Double diferenciaKm) {
        this.diferenciaKm = diferenciaKm;
    }

    public Long getTanqueo() {
        return tanqueo;
    }

    public void setTanqueo(Long tanqueo) {
        Long oldTanqueo = this.tanqueo;
        this.tanqueo = tanqueo;
        changeSupport.firePropertyChange("tanqueo", oldTanqueo, tanqueo);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        String oldObservaciones = this.observaciones;
        this.observaciones = observaciones;
        changeSupport.firePropertyChange("observaciones", oldObservaciones, observaciones);
    }

    public String getTipoRuta() {
        return tipoRuta;
    }

    public void setTipoRuta(String tipoRuta) {
        this.tipoRuta = tipoRuta;
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

    public Vehiculo getPlaca() {
        return placa;
    }

    public void setPlaca(Vehiculo placa) {
        Vehiculo oldPlaca = this.placa;
        this.placa = placa;
        changeSupport.firePropertyChange("placa", oldPlaca, placa);
    }

    public ActividadPrincipal getIdActividadPrincipal() {
        return idActividadPrincipal;
    }

    public void setIdActividadPrincipal(ActividadPrincipal idActividadPrincipal) {
        ActividadPrincipal oldIdActividadPrincipal = this.idActividadPrincipal;
        this.idActividadPrincipal = idActividadPrincipal;
        changeSupport.firePropertyChange("idActividadPrincipal", oldIdActividadPrincipal, idActividadPrincipal);
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        Proyecto oldIdProyecto = this.idProyecto;
        this.idProyecto = idProyecto;
        changeSupport.firePropertyChange("idProyecto", oldIdProyecto, idProyecto);
    }

    public UsuarioProyecto getIdUsuarioProyecto() {
        return idUsuarioProyecto;
    }

    public void setIdUsuarioProyecto(UsuarioProyecto idUsuarioProyecto) {
        UsuarioProyecto oldIdUsuarioProyecto = this.idUsuarioProyecto;
        this.idUsuarioProyecto = idUsuarioProyecto;
        changeSupport.firePropertyChange("idUsuarioProyecto", oldIdUsuarioProyecto, idUsuarioProyecto);
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        Empleado oldIdEmpleado = this.idEmpleado;
        this.idEmpleado = idEmpleado;
        changeSupport.firePropertyChange("idEmpleado", oldIdEmpleado, idEmpleado);
    }

    @XmlTransient
    public Collection<Novedad> getNovedadCollection() {
        return novedadCollection;
    }

    public void setNovedadCollection(Collection<Novedad> novedadCollection) {
        this.novedadCollection = novedadCollection;
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
        hash += (idReporteDiario != null ? idReporteDiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteDiario)) {
            return false;
        }
        ReporteDiario other = (ReporteDiario) object;
        if ((this.idReporteDiario == null && other.idReporteDiario != null) || (this.idReporteDiario != null && !this.idReporteDiario.equals(other.idReporteDiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.ReporteDiario[ idReporteDiario=" + idReporteDiario + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
