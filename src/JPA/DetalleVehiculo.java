/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhorm
 */
@Entity
@Table(name = "detalle_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVehiculo.findAll", query = "SELECT d FROM DetalleVehiculo d"),
    @NamedQuery(name = "DetalleVehiculo.findByIdDetalleVehiculo", query = "SELECT d FROM DetalleVehiculo d WHERE d.idDetalleVehiculo = :idDetalleVehiculo"),
    @NamedQuery(name = "DetalleVehiculo.findByPlaca", query = "SELECT d FROM DetalleVehiculo d WHERE d.placa = :placa"),
    @NamedQuery(name = "DetalleVehiculo.findByLinea", query = "SELECT d FROM DetalleVehiculo d WHERE d.linea = :linea"),
    @NamedQuery(name = "DetalleVehiculo.findByTipo", query = "SELECT d FROM DetalleVehiculo d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "DetalleVehiculo.findByMarca", query = "SELECT d FROM DetalleVehiculo d WHERE d.marca = :marca"),
    @NamedQuery(name = "DetalleVehiculo.findByColor", query = "SELECT d FROM DetalleVehiculo d WHERE d.color = :color"),
    @NamedQuery(name = "DetalleVehiculo.findByCilindraje", query = "SELECT d FROM DetalleVehiculo d WHERE d.cilindraje = :cilindraje"),
    @NamedQuery(name = "DetalleVehiculo.findByNChasis", query = "SELECT d FROM DetalleVehiculo d WHERE d.nChasis = :nChasis"),
    @NamedQuery(name = "DetalleVehiculo.findByModelo", query = "SELECT d FROM DetalleVehiculo d WHERE d.modelo = :modelo"),
    @NamedQuery(name = "DetalleVehiculo.findByUsoVehiculo", query = "SELECT d FROM DetalleVehiculo d WHERE d.usoVehiculo = :usoVehiculo"),
    @NamedQuery(name = "DetalleVehiculo.findByNMotor", query = "SELECT d FROM DetalleVehiculo d WHERE d.nMotor = :nMotor"),
    @NamedQuery(name = "DetalleVehiculo.findByMNSerie", query = "SELECT d FROM DetalleVehiculo d WHERE d.mNSerie = :mNSerie"),
    @NamedQuery(name = "DetalleVehiculo.findByMFabricante", query = "SELECT d FROM DetalleVehiculo d WHERE d.mFabricante = :mFabricante"),
    @NamedQuery(name = "DetalleVehiculo.findByMCombustible", query = "SELECT d FROM DetalleVehiculo d WHERE d.mCombustible = :mCombustible"),
    @NamedQuery(name = "DetalleVehiculo.findByMLubricante", query = "SELECT d FROM DetalleVehiculo d WHERE d.mLubricante = :mLubricante"),
    @NamedQuery(name = "DetalleVehiculo.findByMOrientacion", query = "SELECT d FROM DetalleVehiculo d WHERE d.mOrientacion = :mOrientacion"),
    @NamedQuery(name = "DetalleVehiculo.findByMTurbo", query = "SELECT d FROM DetalleVehiculo d WHERE d.mTurbo = :mTurbo"),
    @NamedQuery(name = "DetalleVehiculo.findByMNCilidos", query = "SELECT d FROM DetalleVehiculo d WHERE d.mNCilidos = :mNCilidos"),
    @NamedQuery(name = "DetalleVehiculo.findByMNValvulas", query = "SELECT d FROM DetalleVehiculo d WHERE d.mNValvulas = :mNValvulas"),
    @NamedQuery(name = "DetalleVehiculo.findByTModelo", query = "SELECT d FROM DetalleVehiculo d WHERE d.tModelo = :tModelo"),
    @NamedQuery(name = "DetalleVehiculo.findByTTipo", query = "SELECT d FROM DetalleVehiculo d WHERE d.tTipo = :tTipo"),
    @NamedQuery(name = "DetalleVehiculo.findByTNVelocidades", query = "SELECT d FROM DetalleVehiculo d WHERE d.tNVelocidades = :tNVelocidades"),
    @NamedQuery(name = "DetalleVehiculo.findByTSuspDelantera", query = "SELECT d FROM DetalleVehiculo d WHERE d.tSuspDelantera = :tSuspDelantera"),
    @NamedQuery(name = "DetalleVehiculo.findByTSuspTrasera", query = "SELECT d FROM DetalleVehiculo d WHERE d.tSuspTrasera = :tSuspTrasera"),
    @NamedQuery(name = "DetalleVehiculo.findByTNEjes", query = "SELECT d FROM DetalleVehiculo d WHERE d.tNEjes = :tNEjes"),
    @NamedQuery(name = "DetalleVehiculo.findByTNLlantas", query = "SELECT d FROM DetalleVehiculo d WHERE d.tNLlantas = :tNLlantas"),
    @NamedQuery(name = "DetalleVehiculo.findByTDimRines", query = "SELECT d FROM DetalleVehiculo d WHERE d.tDimRines = :tDimRines"),
    @NamedQuery(name = "DetalleVehiculo.findByTTipoDirec", query = "SELECT d FROM DetalleVehiculo d WHERE d.tTipoDirec = :tTipoDirec"),
    @NamedQuery(name = "DetalleVehiculo.findByCFabricante", query = "SELECT d FROM DetalleVehiculo d WHERE d.cFabricante = :cFabricante"),
    @NamedQuery(name = "DetalleVehiculo.findByCReferencia", query = "SELECT d FROM DetalleVehiculo d WHERE d.cReferencia = :cReferencia"),
    @NamedQuery(name = "DetalleVehiculo.findByCCapCarga", query = "SELECT d FROM DetalleVehiculo d WHERE d.cCapCarga = :cCapCarga"),
    @NamedQuery(name = "DetalleVehiculo.findByCGuiasPnlEyec", query = "SELECT d FROM DetalleVehiculo d WHERE d.cGuiasPnlEyec = :cGuiasPnlEyec"),
    @NamedQuery(name = "DetalleVehiculo.findByCGuiasPalaSup", query = "SELECT d FROM DetalleVehiculo d WHERE d.cGuiasPalaSup = :cGuiasPalaSup"),
    @NamedQuery(name = "DetalleVehiculo.findBySeRefBateria", query = "SELECT d FROM DetalleVehiculo d WHERE d.seRefBateria = :seRefBateria"),
    @NamedQuery(name = "DetalleVehiculo.findBySeVoltaje", query = "SELECT d FROM DetalleVehiculo d WHERE d.seVoltaje = :seVoltaje"),
    @NamedQuery(name = "DetalleVehiculo.findBySeAmperaje", query = "SELECT d FROM DetalleVehiculo d WHERE d.seAmperaje = :seAmperaje"),
    @NamedQuery(name = "DetalleVehiculo.findBySeNBateria", query = "SELECT d FROM DetalleVehiculo d WHERE d.seNBateria = :seNBateria"),
    @NamedQuery(name = "DetalleVehiculo.findBySeConexion", query = "SELECT d FROM DetalleVehiculo d WHERE d.seConexion = :seConexion"),
    @NamedQuery(name = "DetalleVehiculo.findByFreAcciona", query = "SELECT d FROM DetalleVehiculo d WHERE d.freAcciona = :freAcciona"),
    @NamedQuery(name = "DetalleVehiculo.findByFreTipoFreDelan", query = "SELECT d FROM DetalleVehiculo d WHERE d.freTipoFreDelan = :freTipoFreDelan"),
    @NamedQuery(name = "DetalleVehiculo.findByFreTipoFreTra", query = "SELECT d FROM DetalleVehiculo d WHERE d.freTipoFreTra = :freTipoFreTra"),
    @NamedQuery(name = "DetalleVehiculo.findByImg1", query = "SELECT d FROM DetalleVehiculo d WHERE d.img1 = :img1"),
    @NamedQuery(name = "DetalleVehiculo.findByImg2", query = "SELECT d FROM DetalleVehiculo d WHERE d.img2 = :img2"),
    @NamedQuery(name = "DetalleVehiculo.findByImg3", query = "SELECT d FROM DetalleVehiculo d WHERE d.img3 = :img3")})
public class DetalleVehiculo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_vehiculo")
    private Long idDetalleVehiculo;
    @Column(name = "placa")
    private String placa;
    @Column(name = "linea")
    private String linea;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "color")
    private String color;
    @Column(name = "cilindraje")
    private String cilindraje;
    @Column(name = "n_chasis")
    private String nChasis;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "uso_vehiculo")
    private String usoVehiculo;
    @Column(name = "n_motor")
    private String nMotor;
    @Column(name = "m_n_serie")
    private String mNSerie;
    @Column(name = "m_fabricante")
    private String mFabricante;
    @Column(name = "m_combustible")
    private String mCombustible;
    @Column(name = "m_lubricante")
    private String mLubricante;
    @Column(name = "m_orientacion")
    private String mOrientacion;
    @Column(name = "m_turbo")
    private String mTurbo;
    @Column(name = "m_n_cilidos")
    private String mNCilidos;
    @Column(name = "m_n_valvulas")
    private String mNValvulas;
    @Column(name = "t_modelo")
    private String tModelo;
    @Column(name = "t_tipo")
    private String tTipo;
    @Column(name = "t_n_velocidades")
    private String tNVelocidades;
    @Column(name = "t_susp_delantera")
    private String tSuspDelantera;
    @Column(name = "t_susp_trasera")
    private String tSuspTrasera;
    @Column(name = "t_n_ejes")
    private String tNEjes;
    @Column(name = "t_n_llantas")
    private String tNLlantas;
    @Column(name = "t_dim_rines")
    private String tDimRines;
    @Column(name = "t_tipo_direc")
    private String tTipoDirec;
    @Column(name = "c_fabricante")
    private String cFabricante;
    @Column(name = "c_referencia")
    private String cReferencia;
    @Column(name = "c_cap_carga")
    private String cCapCarga;
    @Column(name = "c_guias_pnl_eyec")
    private String cGuiasPnlEyec;
    @Column(name = "c_guias_pala_sup")
    private String cGuiasPalaSup;
    @Column(name = "se_ref_bateria")
    private String seRefBateria;
    @Column(name = "se_voltaje")
    private String seVoltaje;
    @Column(name = "se_amperaje")
    private String seAmperaje;
    @Column(name = "se_n_bateria")
    private String seNBateria;
    @Column(name = "se_conexion")
    private String seConexion;
    @Column(name = "fre_acciona")
    private String freAcciona;
    @Column(name = "fre_tipo_fre_delan")
    private String freTipoFreDelan;
    @Column(name = "fre_tipo_fre_tra")
    private String freTipoFreTra;
    @Column(name = "img_1")
    private String img1;
    @Column(name = "img_2")
    private String img2;
    @Column(name = "img_3")
    private String img3;

    public DetalleVehiculo() {
    }

    public DetalleVehiculo(Long idDetalleVehiculo) {
        this.idDetalleVehiculo = idDetalleVehiculo;
    }

    public Long getIdDetalleVehiculo() {
        return idDetalleVehiculo;
    }

    public void setIdDetalleVehiculo(Long idDetalleVehiculo) {
        Long oldIdDetalleVehiculo = this.idDetalleVehiculo;
        this.idDetalleVehiculo = idDetalleVehiculo;
        changeSupport.firePropertyChange("idDetalleVehiculo", oldIdDetalleVehiculo, idDetalleVehiculo);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        String oldPlaca = this.placa;
        this.placa = placa;
        changeSupport.firePropertyChange("placa", oldPlaca, placa);
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        String oldLinea = this.linea;
        this.linea = linea;
        changeSupport.firePropertyChange("linea", oldLinea, linea);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        changeSupport.firePropertyChange("color", oldColor, color);
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        String oldCilindraje = this.cilindraje;
        this.cilindraje = cilindraje;
        changeSupport.firePropertyChange("cilindraje", oldCilindraje, cilindraje);
    }

    public String getNChasis() {
        return nChasis;
    }

    public void setNChasis(String nChasis) {
        String oldNChasis = this.nChasis;
        this.nChasis = nChasis;
        changeSupport.firePropertyChange("NChasis", oldNChasis, nChasis);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        String oldModelo = this.modelo;
        this.modelo = modelo;
        changeSupport.firePropertyChange("modelo", oldModelo, modelo);
    }

    public String getUsoVehiculo() {
        return usoVehiculo;
    }

    public void setUsoVehiculo(String usoVehiculo) {
        String oldUsoVehiculo = this.usoVehiculo;
        this.usoVehiculo = usoVehiculo;
        changeSupport.firePropertyChange("usoVehiculo", oldUsoVehiculo, usoVehiculo);
    }

    public String getNMotor() {
        return nMotor;
    }

    public void setNMotor(String nMotor) {
        String oldNMotor = this.nMotor;
        this.nMotor = nMotor;
        changeSupport.firePropertyChange("NMotor", oldNMotor, nMotor);
    }

    public String getMNSerie() {
        return mNSerie;
    }

    public void setMNSerie(String mNSerie) {
        String oldMNSerie = this.mNSerie;
        this.mNSerie = mNSerie;
        changeSupport.firePropertyChange("MNSerie", oldMNSerie, mNSerie);
    }

    public String getMFabricante() {
        return mFabricante;
    }

    public void setMFabricante(String mFabricante) {
        String oldMFabricante = this.mFabricante;
        this.mFabricante = mFabricante;
        changeSupport.firePropertyChange("MFabricante", oldMFabricante, mFabricante);
    }

    public String getMCombustible() {
        return mCombustible;
    }

    public void setMCombustible(String mCombustible) {
        String oldMCombustible = this.mCombustible;
        this.mCombustible = mCombustible;
        changeSupport.firePropertyChange("MCombustible", oldMCombustible, mCombustible);
    }

    public String getMLubricante() {
        return mLubricante;
    }

    public void setMLubricante(String mLubricante) {
        String oldMLubricante = this.mLubricante;
        this.mLubricante = mLubricante;
        changeSupport.firePropertyChange("MLubricante", oldMLubricante, mLubricante);
    }

    public String getMOrientacion() {
        return mOrientacion;
    }

    public void setMOrientacion(String mOrientacion) {
        String oldMOrientacion = this.mOrientacion;
        this.mOrientacion = mOrientacion;
        changeSupport.firePropertyChange("MOrientacion", oldMOrientacion, mOrientacion);
    }

    public String getMTurbo() {
        return mTurbo;
    }

    public void setMTurbo(String mTurbo) {
        String oldMTurbo = this.mTurbo;
        this.mTurbo = mTurbo;
        changeSupport.firePropertyChange("MTurbo", oldMTurbo, mTurbo);
    }

    public String getMNCilidos() {
        return mNCilidos;
    }

    public void setMNCilidos(String mNCilidos) {
        String oldMNCilidos = this.mNCilidos;
        this.mNCilidos = mNCilidos;
        changeSupport.firePropertyChange("MNCilidos", oldMNCilidos, mNCilidos);
    }

    public String getMNValvulas() {
        return mNValvulas;
    }

    public void setMNValvulas(String mNValvulas) {
        String oldMNValvulas = this.mNValvulas;
        this.mNValvulas = mNValvulas;
        changeSupport.firePropertyChange("MNValvulas", oldMNValvulas, mNValvulas);
    }

    public String getTModelo() {
        return tModelo;
    }

    public void setTModelo(String tModelo) {
        String oldTModelo = this.tModelo;
        this.tModelo = tModelo;
        changeSupport.firePropertyChange("TModelo", oldTModelo, tModelo);
    }

    public String getTTipo() {
        return tTipo;
    }

    public void setTTipo(String tTipo) {
        String oldTTipo = this.tTipo;
        this.tTipo = tTipo;
        changeSupport.firePropertyChange("TTipo", oldTTipo, tTipo);
    }

    public String getTNVelocidades() {
        return tNVelocidades;
    }

    public void setTNVelocidades(String tNVelocidades) {
        String oldTNVelocidades = this.tNVelocidades;
        this.tNVelocidades = tNVelocidades;
        changeSupport.firePropertyChange("TNVelocidades", oldTNVelocidades, tNVelocidades);
    }

    public String getTSuspDelantera() {
        return tSuspDelantera;
    }

    public void setTSuspDelantera(String tSuspDelantera) {
        String oldTSuspDelantera = this.tSuspDelantera;
        this.tSuspDelantera = tSuspDelantera;
        changeSupport.firePropertyChange("TSuspDelantera", oldTSuspDelantera, tSuspDelantera);
    }

    public String getTSuspTrasera() {
        return tSuspTrasera;
    }

    public void setTSuspTrasera(String tSuspTrasera) {
        String oldTSuspTrasera = this.tSuspTrasera;
        this.tSuspTrasera = tSuspTrasera;
        changeSupport.firePropertyChange("TSuspTrasera", oldTSuspTrasera, tSuspTrasera);
    }

    public String getTNEjes() {
        return tNEjes;
    }

    public void setTNEjes(String tNEjes) {
        String oldTNEjes = this.tNEjes;
        this.tNEjes = tNEjes;
        changeSupport.firePropertyChange("TNEjes", oldTNEjes, tNEjes);
    }

    public String getTNLlantas() {
        return tNLlantas;
    }

    public void setTNLlantas(String tNLlantas) {
        String oldTNLlantas = this.tNLlantas;
        this.tNLlantas = tNLlantas;
        changeSupport.firePropertyChange("TNLlantas", oldTNLlantas, tNLlantas);
    }

    public String getTDimRines() {
        return tDimRines;
    }

    public void setTDimRines(String tDimRines) {
        String oldTDimRines = this.tDimRines;
        this.tDimRines = tDimRines;
        changeSupport.firePropertyChange("TDimRines", oldTDimRines, tDimRines);
    }

    public String getTTipoDirec() {
        return tTipoDirec;
    }

    public void setTTipoDirec(String tTipoDirec) {
        String oldTTipoDirec = this.tTipoDirec;
        this.tTipoDirec = tTipoDirec;
        changeSupport.firePropertyChange("TTipoDirec", oldTTipoDirec, tTipoDirec);
    }

    public String getCFabricante() {
        return cFabricante;
    }

    public void setCFabricante(String cFabricante) {
        String oldCFabricante = this.cFabricante;
        this.cFabricante = cFabricante;
        changeSupport.firePropertyChange("CFabricante", oldCFabricante, cFabricante);
    }

    public String getCReferencia() {
        return cReferencia;
    }

    public void setCReferencia(String cReferencia) {
        String oldCReferencia = this.cReferencia;
        this.cReferencia = cReferencia;
        changeSupport.firePropertyChange("CReferencia", oldCReferencia, cReferencia);
    }

    public String getCCapCarga() {
        return cCapCarga;
    }

    public void setCCapCarga(String cCapCarga) {
        String oldCCapCarga = this.cCapCarga;
        this.cCapCarga = cCapCarga;
        changeSupport.firePropertyChange("CCapCarga", oldCCapCarga, cCapCarga);
    }

    public String getCGuiasPnlEyec() {
        return cGuiasPnlEyec;
    }

    public void setCGuiasPnlEyec(String cGuiasPnlEyec) {
        String oldCGuiasPnlEyec = this.cGuiasPnlEyec;
        this.cGuiasPnlEyec = cGuiasPnlEyec;
        changeSupport.firePropertyChange("CGuiasPnlEyec", oldCGuiasPnlEyec, cGuiasPnlEyec);
    }

    public String getCGuiasPalaSup() {
        return cGuiasPalaSup;
    }

    public void setCGuiasPalaSup(String cGuiasPalaSup) {
        String oldCGuiasPalaSup = this.cGuiasPalaSup;
        this.cGuiasPalaSup = cGuiasPalaSup;
        changeSupport.firePropertyChange("CGuiasPalaSup", oldCGuiasPalaSup, cGuiasPalaSup);
    }

    public String getSeRefBateria() {
        return seRefBateria;
    }

    public void setSeRefBateria(String seRefBateria) {
        String oldSeRefBateria = this.seRefBateria;
        this.seRefBateria = seRefBateria;
        changeSupport.firePropertyChange("seRefBateria", oldSeRefBateria, seRefBateria);
    }

    public String getSeVoltaje() {
        return seVoltaje;
    }

    public void setSeVoltaje(String seVoltaje) {
        String oldSeVoltaje = this.seVoltaje;
        this.seVoltaje = seVoltaje;
        changeSupport.firePropertyChange("seVoltaje", oldSeVoltaje, seVoltaje);
    }

    public String getSeAmperaje() {
        return seAmperaje;
    }

    public void setSeAmperaje(String seAmperaje) {
        String oldSeAmperaje = this.seAmperaje;
        this.seAmperaje = seAmperaje;
        changeSupport.firePropertyChange("seAmperaje", oldSeAmperaje, seAmperaje);
    }

    public String getSeNBateria() {
        return seNBateria;
    }

    public void setSeNBateria(String seNBateria) {
        String oldSeNBateria = this.seNBateria;
        this.seNBateria = seNBateria;
        changeSupport.firePropertyChange("seNBateria", oldSeNBateria, seNBateria);
    }

    public String getSeConexion() {
        return seConexion;
    }

    public void setSeConexion(String seConexion) {
        String oldSeConexion = this.seConexion;
        this.seConexion = seConexion;
        changeSupport.firePropertyChange("seConexion", oldSeConexion, seConexion);
    }

    public String getFreAcciona() {
        return freAcciona;
    }

    public void setFreAcciona(String freAcciona) {
        String oldFreAcciona = this.freAcciona;
        this.freAcciona = freAcciona;
        changeSupport.firePropertyChange("freAcciona", oldFreAcciona, freAcciona);
    }

    public String getFreTipoFreDelan() {
        return freTipoFreDelan;
    }

    public void setFreTipoFreDelan(String freTipoFreDelan) {
        String oldFreTipoFreDelan = this.freTipoFreDelan;
        this.freTipoFreDelan = freTipoFreDelan;
        changeSupport.firePropertyChange("freTipoFreDelan", oldFreTipoFreDelan, freTipoFreDelan);
    }

    public String getFreTipoFreTra() {
        return freTipoFreTra;
    }

    public void setFreTipoFreTra(String freTipoFreTra) {
        String oldFreTipoFreTra = this.freTipoFreTra;
        this.freTipoFreTra = freTipoFreTra;
        changeSupport.firePropertyChange("freTipoFreTra", oldFreTipoFreTra, freTipoFreTra);
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        String oldImg1 = this.img1;
        this.img1 = img1;
        changeSupport.firePropertyChange("img1", oldImg1, img1);
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        String oldImg2 = this.img2;
        this.img2 = img2;
        changeSupport.firePropertyChange("img2", oldImg2, img2);
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        String oldImg3 = this.img3;
        this.img3 = img3;
        changeSupport.firePropertyChange("img3", oldImg3, img3);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleVehiculo != null ? idDetalleVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVehiculo)) {
            return false;
        }
        DetalleVehiculo other = (DetalleVehiculo) object;
        if ((this.idDetalleVehiculo == null && other.idDetalleVehiculo != null) || (this.idDetalleVehiculo != null && !this.idDetalleVehiculo.equals(other.idDetalleVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.DetalleVehiculo[ idDetalleVehiculo=" + idDetalleVehiculo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
