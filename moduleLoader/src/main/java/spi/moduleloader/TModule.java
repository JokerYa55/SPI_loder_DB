/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spi.moduleloader;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_module")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TModule.findAll", query = "SELECT t FROM TModule t")
    , @NamedQuery(name = "TModule.findById", query = "SELECT t FROM TModule t WHERE t.id = :id")})
public class TModule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "f_module")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] fModule;

    public TModule() {
    }

    public TModule(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFModule() {
        return fModule;
    }

    public void setFModule(byte[] fModule) {
        this.fModule = fModule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TModule)) {
            return false;
        }
        TModule other = (TModule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "spi.moduleloader.TModule[ id=" + id + " ]";
    }
    
}
