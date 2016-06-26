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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FaruckJ
 */
@Entity
@Table(name = "usuario_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByIdUsuarioRol", query = "SELECT u FROM UsuarioRol u WHERE u.idUsuarioRol = :idUsuarioRol")})
public class UsuarioRol implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_rol")
    private Long idUsuarioRol;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRolUsuario;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public UsuarioRol() {
    }

    public UsuarioRol(Long idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Long getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Long idUsuarioRol) {
        Long oldIdUsuarioRol = this.idUsuarioRol;
        this.idUsuarioRol = idUsuarioRol;
        changeSupport.firePropertyChange("idUsuarioRol", oldIdUsuarioRol, idUsuarioRol);
    }

    public Rol getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Rol idRolUsuario) {
        Rol oldIdRolUsuario = this.idRolUsuario;
        this.idRolUsuario = idRolUsuario;
        changeSupport.firePropertyChange("idRolUsuario", oldIdRolUsuario, idRolUsuario);
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        Usuario oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        changeSupport.firePropertyChange("idUsuario", oldIdUsuario, idUsuario);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRol != null ? idUsuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.UsuarioRol[ idUsuarioRol=" + idUsuarioRol + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
