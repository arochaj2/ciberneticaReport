/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dell
 */
@Embeddable
public class CrrReclamantesPK implements Serializable {

    @Column(name = "numeroreclamo")
    private String numeroreclamo;
    @Column(name = "numeroreclamante")
    private Integer numeroreclamante;

    public CrrReclamantesPK() {
    }

    public CrrReclamantesPK(String numeroreclamo, Integer numeroreclamante) {
        this.numeroreclamo = numeroreclamo;
        this.numeroreclamante = numeroreclamante;
    }

    public String getNumeroreclamo() {
        return numeroreclamo;
    }

    public void setNumeroreclamo(String numeroreclamo) {
        this.numeroreclamo = numeroreclamo;
    }

    public Integer getNumeroreclamante() {
        return numeroreclamante;
    }

    public void setNumeroreclamante(Integer numeroreclamante) {
        this.numeroreclamante = numeroreclamante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroreclamo != null ? numeroreclamo.hashCode() : 0);
        hash += (numeroreclamante != null ? numeroreclamante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrrReclamantesPK)) {
            return false;
        }
        CrrReclamantesPK other = (CrrReclamantesPK) object;
        if ((this.numeroreclamo == null && other.numeroreclamo != null) || (this.numeroreclamo != null && !this.numeroreclamo.equals(other.numeroreclamo))) {
            return false;
        }
        if ((this.numeroreclamante == null && other.numeroreclamante != null) || (this.numeroreclamante != null && !this.numeroreclamante.equals(other.numeroreclamante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.CrrReclamantesPK[ numeroreclamo=" + numeroreclamo + ", numeroreclamante=" + numeroreclamante + " ]";
    }
    
}
