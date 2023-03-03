/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "CRR_RECLAMO")
@NamedQueries({
    @NamedQuery(name = "CrrReclamo.findAll", query = "SELECT c FROM CrrReclamo c"),
    @NamedQuery(name = "CrrReclamo.findByNumeroreclamo", query = "SELECT c FROM CrrReclamo c WHERE c.numeroreclamo = :numeroreclamo"),
    @NamedQuery(name = "CrrReclamo.findByFecharegistro", query = "SELECT c FROM CrrReclamo c WHERE c.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "CrrReclamo.findByUsuarioregistro", query = "SELECT c FROM CrrReclamo c WHERE c.usuarioregistro = :usuarioregistro"),
    @NamedQuery(name = "CrrReclamo.findByFechareclamo", query = "SELECT c FROM CrrReclamo c WHERE c.fechareclamo = :fechareclamo"),
    @NamedQuery(name = "CrrReclamo.findByUsuarioreclamo", query = "SELECT c FROM CrrReclamo c WHERE c.usuarioreclamo = :usuarioreclamo"),
    @NamedQuery(name = "CrrReclamo.findByFachasiniestro", query = "SELECT c FROM CrrReclamo c WHERE c.fachasiniestro = :fachasiniestro"),
    @NamedQuery(name = "CrrReclamo.findByTiposiniestro", query = "SELECT c FROM CrrReclamo c WHERE c.tiposiniestro = :tiposiniestro"),
    @NamedQuery(name = "CrrReclamo.findByDescripcion", query = "SELECT c FROM CrrReclamo c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CrrReclamo.findByNumparte", query = "SELECT c FROM CrrReclamo c WHERE c.numparte = :numparte"),
    @NamedQuery(name = "CrrReclamo.findByNumresolucion", query = "SELECT c FROM CrrReclamo c WHERE c.numresolucion = :numresolucion"),
    @NamedQuery(name = "CrrReclamo.findByNumdenuncia", query = "SELECT c FROM CrrReclamo c WHERE c.numdenuncia = :numdenuncia"),
    @NamedQuery(name = "CrrReclamo.findByNumoficio", query = "SELECT c FROM CrrReclamo c WHERE c.numoficio = :numoficio"),
    @NamedQuery(name = "CrrReclamo.findByNombrecorredor", query = "SELECT c FROM CrrReclamo c WHERE c.nombrecorredor = :nombrecorredor"),
    @NamedQuery(name = "CrrReclamo.findByEstadosiniestro", query = "SELECT c FROM CrrReclamo c WHERE c.estadosiniestro = :estadosiniestro"),
    @NamedQuery(name = "CrrReclamo.findByUbicacionsiniestro", query = "SELECT c FROM CrrReclamo c WHERE c.ubicacionsiniestro = :ubicacionsiniestro"),
    @NamedQuery(name = "CrrReclamo.findByNumeroplacapolicia", query = "SELECT c FROM CrrReclamo c WHERE c.numeroplacapolicia = :numeroplacapolicia"),
    @NamedQuery(name = "CrrReclamo.findByFoto", query = "SELECT c FROM CrrReclamo c WHERE c.foto = :foto"),
    @NamedQuery(name = "CrrReclamo.findByCoaseguradp", query = "SELECT c FROM CrrReclamo c WHERE c.coaseguradp = :coaseguradp"),
    @NamedQuery(name = "CrrReclamo.findByFechaCarga", query = "SELECT c FROM CrrReclamo c WHERE c.fechaCarga = :fechaCarga")})
public class CrrReclamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "numeroreclamo")
    private String numeroreclamo;
    @Column(name = "fecharegistro")
    private String fecharegistro;
    @Column(name = "usuarioregistro")
    private String usuarioregistro;
    @Column(name = "fechareclamo")
    private String fechareclamo;
    @Column(name = "usuarioreclamo")
    private String usuarioreclamo;
    @Column(name = "fachasiniestro")
    private String fachasiniestro;
    @Column(name = "tiposiniestro")
    private Integer tiposiniestro;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "numparte")
    private String numparte;
    @Column(name = "numresolucion")
    private String numresolucion;
    @Column(name = "numdenuncia")
    private String numdenuncia;
    @Column(name = "numoficio")
    private String numoficio;
    @Column(name = "nombrecorredor")
    private String nombrecorredor;
    @Column(name = "estadosiniestro")
    private String estadosiniestro;
    @Column(name = "ubicacionsiniestro")
    private String ubicacionsiniestro;
    @Column(name = "numeroplacapolicia")
    private String numeroplacapolicia;
    @Column(name = "foto")
    private String foto;
    @Column(name = "coaseguradp")
    private String coaseguradp;
    @Column(name = "fecha_carga")
    private String fechaCarga;
    @JoinColumn(name = "codaseguradora", referencedColumnName = "Aseguradora_id")
    @ManyToOne
    private Aseguradoras codaseguradora;
    @OneToMany(mappedBy = "crrReclamo" ,fetch = FetchType.EAGER)
    private List<CrrReclamantes> crrReclamantesList;

    public CrrReclamo() {
    }

    public CrrReclamo(String numeroreclamo) {
        this.numeroreclamo = numeroreclamo;
    }

    public String getNumeroreclamo() {
        return numeroreclamo;
    }

    public void setNumeroreclamo(String numeroreclamo) {
        this.numeroreclamo = numeroreclamo;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getUsuarioregistro() {
        return usuarioregistro;
    }

    public void setUsuarioregistro(String usuarioregistro) {
        this.usuarioregistro = usuarioregistro;
    }

    public String getFechareclamo() {
        return fechareclamo;
    }

    public void setFechareclamo(String fechareclamo) {
        this.fechareclamo = fechareclamo;
    }

    public String getUsuarioreclamo() {
        return usuarioreclamo;
    }

    public void setUsuarioreclamo(String usuarioreclamo) {
        this.usuarioreclamo = usuarioreclamo;
    }

    public String getFachasiniestro() {
        return fachasiniestro;
    }

    public void setFachasiniestro(String fachasiniestro) {
        this.fachasiniestro = fachasiniestro;
    }

    public Integer getTiposiniestro() {
        return tiposiniestro;
    }

    public void setTiposiniestro(Integer tiposiniestro) {
        this.tiposiniestro = tiposiniestro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumparte() {
        return numparte;
    }

    public void setNumparte(String numparte) {
        this.numparte = numparte;
    }

    public String getNumresolucion() {
        return numresolucion;
    }

    public void setNumresolucion(String numresolucion) {
        this.numresolucion = numresolucion;
    }

    public String getNumdenuncia() {
        return numdenuncia;
    }

    public void setNumdenuncia(String numdenuncia) {
        this.numdenuncia = numdenuncia;
    }

    public String getNumoficio() {
        return numoficio;
    }

    public void setNumoficio(String numoficio) {
        this.numoficio = numoficio;
    }

    public String getNombrecorredor() {
        return nombrecorredor;
    }

    public void setNombrecorredor(String nombrecorredor) {
        this.nombrecorredor = nombrecorredor;
    }

    public String getEstadosiniestro() {
        return estadosiniestro;
    }

    public void setEstadosiniestro(String estadosiniestro) {
        this.estadosiniestro = estadosiniestro;
    }

    public String getUbicacionsiniestro() {
        return ubicacionsiniestro;
    }

    public void setUbicacionsiniestro(String ubicacionsiniestro) {
        this.ubicacionsiniestro = ubicacionsiniestro;
    }

    public String getNumeroplacapolicia() {
        return numeroplacapolicia;
    }

    public void setNumeroplacapolicia(String numeroplacapolicia) {
        this.numeroplacapolicia = numeroplacapolicia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCoaseguradp() {
        return coaseguradp;
    }

    public void setCoaseguradp(String coaseguradp) {
        this.coaseguradp = coaseguradp;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Aseguradoras getCodaseguradora() {
        return codaseguradora;
    }

    public void setCodaseguradora(Aseguradoras codaseguradora) {
        this.codaseguradora = codaseguradora;
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
        hash += (numeroreclamo != null ? numeroreclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrrReclamo)) {
            return false;
        }
        CrrReclamo other = (CrrReclamo) object;
        if ((this.numeroreclamo == null && other.numeroreclamo != null) || (this.numeroreclamo != null && !this.numeroreclamo.equals(other.numeroreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.CrrReclamo[ numeroreclamo=" + numeroreclamo + " ]";
    }
    
}
