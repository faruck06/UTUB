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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Faruck
 */
@Entity
@Table(name = "ceco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ceco.findAll", query = "SELECT c FROM Ceco c"),
    @NamedQuery(name = "Ceco.findByIdCeco", query = "SELECT c FROM Ceco c WHERE c.idCeco = :idCeco"),
    @NamedQuery(name = "Ceco.findByNombre", query = "SELECT c FROM Ceco c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Ceco.findByTipo", query = "SELECT c FROM Ceco c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Ceco.findByCodigo", query = "SELECT c FROM Ceco c WHERE c.codigo = :codigo")})
public class Ceco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ceco")
    private Integer idCeco;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;

    public Ceco() {
    }

    public Ceco(Integer idCeco) {
        this.idCeco = idCeco;
    }

    public Ceco(Integer idCeco, String nombre, String tipo, String codigo) {
        this.idCeco = idCeco;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public Integer getIdCeco() {
        return idCeco;
    }

    public void setIdCeco(Integer idCeco) {
        this.idCeco = idCeco;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCeco != null ? idCeco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ceco)) {
            return false;
        }
        Ceco other = (Ceco) object;
        if ((this.idCeco == null && other.idCeco != null) || (this.idCeco != null && !this.idCeco.equals(other.idCeco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utub.JPA.Ceco[ idCeco=" + idCeco + " ]";
    }
    
}
