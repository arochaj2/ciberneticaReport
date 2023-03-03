/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "Aseguradoras")
@NamedQueries({
    @NamedQuery(name = "Aseguradoras.findAll", query = "SELECT a FROM Aseguradoras a"),
    @NamedQuery(name = "Aseguradoras.findByAseguradoraid", query = "SELECT a FROM Aseguradoras a WHERE a.aseguradoraid = :aseguradoraid"),
    @NamedQuery(name = "Aseguradoras.findByNombre", query = "SELECT a FROM Aseguradoras a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Aseguradoras.findByParticipaProyecto", query = "SELECT a FROM Aseguradoras a WHERE a.participaProyecto = :participaProyecto"),
    @NamedQuery(name = "Aseguradoras.findByNombrecorto", query = "SELECT a FROM Aseguradoras a WHERE a.nombrecorto = :nombrecorto"),
    @NamedQuery(name = "Aseguradoras.findByProteccion", query = "SELECT a FROM Aseguradoras a WHERE a.proteccion = :proteccion"),
    @NamedQuery(name = "Aseguradoras.findByActivo", query = "SELECT a FROM Aseguradoras a WHERE a.activo = :activo")})
public class Aseguradoras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Aseguradora_id")
    private Integer aseguradoraid;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "participa_proyecto")
    private String participaProyecto;
    @Column(name = "Nombre_corto")
    private String nombrecorto;
    @Column(name = "Proteccion")
    private String proteccion;
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codaseguradora")
    private List<CrrReclamo> crrReclamoList;
    @OneToMany(mappedBy = "codaseguradora")
    private List<CrrReclamantes> crrReclamantesList;

    public Aseguradoras() {
    }

    public Aseguradoras(Integer aseguradoraid) {
        this.aseguradoraid = aseguradoraid;
    }

    public Integer getAseguradoraid() {
        return aseguradoraid;
    }

    public void setAseguradoraid(Integer aseguradoraid) {
        this.aseguradoraid = aseguradoraid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParticipaProyecto() {
        return participaProyecto;
    }

    public void setParticipaProyecto(String participaProyecto) {
        this.participaProyecto = participaProyecto;
    }

    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
    }

    public String getProteccion() {
        return proteccion;
    }

    public void setProteccion(String proteccion) {
        this.proteccion = proteccion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<CrrReclamo> getCrrReclamoList() {
        return crrReclamoList;
    }

    public void setCrrReclamoList(List<CrrReclamo> crrReclamoList) {
        this.crrReclamoList = crrReclamoList;
    }

    public List<CrrReclamantes> getCrrReclamantesList() {
        return crrReclamantesList;
    }

    public void setCrrReclamantesList(List<CrrReclamantes> crrReclamantesList) {
        this.crrReclamantesList = crrReclamantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aseguradoraid != null ? aseguradoraid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aseguradoras)) {
            return false;
        }
        Aseguradoras other = (Aseguradoras) object;
        if ((this.aseguradoraid == null && other.aseguradoraid != null) || (this.aseguradoraid != null && !this.aseguradoraid.equals(other.aseguradoraid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Aseguradoras[ aseguradoraid=" + aseguradoraid + " ]";
    }
    
}
