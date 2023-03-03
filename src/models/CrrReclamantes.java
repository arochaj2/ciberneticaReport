/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "CRR_RECLAMANTES")
@NamedQueries({
    @NamedQuery(name = "CrrReclamantes.findAll", query = "SELECT c FROM CrrReclamantes c"),
    @NamedQuery(name = "CrrReclamantes.findByNumeroreclamo", query = "SELECT c FROM CrrReclamantes c WHERE c.crrReclamantesPK.numeroreclamo = :numeroreclamo"),
    @NamedQuery(name = "CrrReclamantes.findByNumeroreclamante", query = "SELECT c FROM CrrReclamantes c WHERE c.crrReclamantesPK.numeroreclamante = :numeroreclamante"),
    @NamedQuery(name = "CrrReclamantes.findByTiporeclamante", query = "SELECT c FROM CrrReclamantes c WHERE c.tiporeclamante = :tiporeclamante"),
    @NamedQuery(name = "CrrReclamantes.findByFechaRaclamo", query = "SELECT c FROM CrrReclamantes c WHERE c.fechaRaclamo = :fechaRaclamo"),
    @NamedQuery(name = "CrrReclamantes.findByMonto", query = "SELECT c FROM CrrReclamantes c WHERE c.monto = :monto"),
    @NamedQuery(name = "CrrReclamantes.findByFoto", query = "SELECT c FROM CrrReclamantes c WHERE c.foto = :foto"),
    @NamedQuery(name = "CrrReclamantes.findByDescripcion", query = "SELECT c FROM CrrReclamantes c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CrrReclamantes.findByObservacion", query = "SELECT c FROM CrrReclamantes c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CrrReclamantes.findByEstadoreclamo", query = "SELECT c FROM CrrReclamantes c WHERE c.estadoreclamo = :estadoreclamo"),
    @NamedQuery(name = "CrrReclamantes.findBySubrogacion", query = "SELECT c FROM CrrReclamantes c WHERE c.subrogacion = :subrogacion"),
    @NamedQuery(name = "CrrReclamantes.findByIdaseguradorasub", query = "SELECT c FROM CrrReclamantes c WHERE c.idaseguradorasub = :idaseguradorasub"),
    @NamedQuery(name = "CrrReclamantes.findByTipopago", query = "SELECT c FROM CrrReclamantes c WHERE c.tipopago = :tipopago"),
    @NamedQuery(name = "CrrReclamantes.findByTaller", query = "SELECT c FROM CrrReclamantes c WHERE c.taller = :taller"),
    @NamedQuery(name = "CrrReclamantes.findByProvincia", query = "SELECT c FROM CrrReclamantes c WHERE c.provincia = :provincia"),
    @NamedQuery(name = "CrrReclamantes.findByInicial", query = "SELECT c FROM CrrReclamantes c WHERE c.inicial = :inicial"),
    @NamedQuery(name = "CrrReclamantes.findByTomo", query = "SELECT c FROM CrrReclamantes c WHERE c.tomo = :tomo"),
    @NamedQuery(name = "CrrReclamantes.findByFolio", query = "SELECT c FROM CrrReclamantes c WHERE c.folio = :folio"),
    @NamedQuery(name = "CrrReclamantes.findByAsiento", query = "SELECT c FROM CrrReclamantes c WHERE c.asiento = :asiento"),
    @NamedQuery(name = "CrrReclamantes.findByCedularuccorrida", query = "SELECT c FROM CrrReclamantes c WHERE c.cedularuccorrida = :cedularuccorrida"),
    @NamedQuery(name = "CrrReclamantes.findByTipopersona", query = "SELECT c FROM CrrReclamantes c WHERE c.tipopersona = :tipopersona"),
    @NamedQuery(name = "CrrReclamantes.findByPrimernombre", query = "SELECT c FROM CrrReclamantes c WHERE c.primernombre = :primernombre"),
    @NamedQuery(name = "CrrReclamantes.findBySegundonombre", query = "SELECT c FROM CrrReclamantes c WHERE c.segundonombre = :segundonombre"),
    @NamedQuery(name = "CrrReclamantes.findByPrimerapellido", query = "SELECT c FROM CrrReclamantes c WHERE c.primerapellido = :primerapellido"),
    @NamedQuery(name = "CrrReclamantes.findBySegundoapelliddo", query = "SELECT c FROM CrrReclamantes c WHERE c.segundoapelliddo = :segundoapelliddo"),
    @NamedQuery(name = "CrrReclamantes.findByApellidocasada", query = "SELECT c FROM CrrReclamantes c WHERE c.apellidocasada = :apellidocasada"),
    @NamedQuery(name = "CrrReclamantes.findByCodcliente", query = "SELECT c FROM CrrReclamantes c WHERE c.codcliente = :codcliente"),
    @NamedQuery(name = "CrrReclamantes.findByNumplaca", query = "SELECT c FROM CrrReclamantes c WHERE c.numplaca = :numplaca"),
    @NamedQuery(name = "CrrReclamantes.findByNumchasis", query = "SELECT c FROM CrrReclamantes c WHERE c.numchasis = :numchasis"),
    @NamedQuery(name = "CrrReclamantes.findByNummotor", query = "SELECT c FROM CrrReclamantes c WHERE c.nummotor = :nummotor"),
    @NamedQuery(name = "CrrReclamantes.findByVin", query = "SELECT c FROM CrrReclamantes c WHERE c.vin = :vin"),
    @NamedQuery(name = "CrrReclamantes.findByCupo", query = "SELECT c FROM CrrReclamantes c WHERE c.cupo = :cupo"),
    @NamedQuery(name = "CrrReclamantes.findByMarca", query = "SELECT c FROM CrrReclamantes c WHERE c.marca = :marca"),
    @NamedQuery(name = "CrrReclamantes.findByModelo", query = "SELECT c FROM CrrReclamantes c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "CrrReclamantes.findByAnno", query = "SELECT c FROM CrrReclamantes c WHERE c.anno = :anno"),
    @NamedQuery(name = "CrrReclamantes.findByColor", query = "SELECT c FROM CrrReclamantes c WHERE c.color = :color"),
    @NamedQuery(name = "CrrReclamantes.findByEstilo", query = "SELECT c FROM CrrReclamantes c WHERE c.estilo = :estilo"),
    @NamedQuery(name = "CrrReclamantes.findByHipoteca", query = "SELECT c FROM CrrReclamantes c WHERE c.hipoteca = :hipoteca"),
    @NamedQuery(name = "CrrReclamantes.findByAgenciahipotecaria", query = "SELECT c FROM CrrReclamantes c WHERE c.agenciahipotecaria = :agenciahipotecaria"),
    @NamedQuery(name = "CrrReclamantes.findByPerdidatotal", query = "SELECT c FROM CrrReclamantes c WHERE c.perdidatotal = :perdidatotal"),
    @NamedQuery(name = "CrrReclamantes.findByEstadoauto", query = "SELECT c FROM CrrReclamantes c WHERE c.estadoauto = :estadoauto"),
    @NamedQuery(name = "CrrReclamantes.findByProvinciaconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.provinciaconductor = :provinciaconductor"),
    @NamedQuery(name = "CrrReclamantes.findByInicialconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.inicialconductor = :inicialconductor"),
    @NamedQuery(name = "CrrReclamantes.findByTomoconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.tomoconductor = :tomoconductor"),
    @NamedQuery(name = "CrrReclamantes.findByFolioconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.folioconductor = :folioconductor"),
    @NamedQuery(name = "CrrReclamantes.findByCedulacorridaconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.cedulacorridaconductor = :cedulacorridaconductor"),
    @NamedQuery(name = "CrrReclamantes.findByPrimernombreconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.primernombreconductor = :primernombreconductor"),
    @NamedQuery(name = "CrrReclamantes.findBySegundonombreconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.segundonombreconductor = :segundonombreconductor"),
    @NamedQuery(name = "CrrReclamantes.findByPrimerapellidoconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.primerapellidoconductor = :primerapellidoconductor"),
    @NamedQuery(name = "CrrReclamantes.findBySegundoapellidoconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.segundoapellidoconductor = :segundoapellidoconductor"),
    @NamedQuery(name = "CrrReclamantes.findByApellidocasadaconductor", query = "SELECT c FROM CrrReclamantes c WHERE c.apellidocasadaconductor = :apellidocasadaconductor"),
    @NamedQuery(name = "CrrReclamantes.findByFechaCarga", query = "SELECT c FROM CrrReclamantes c WHERE c.fechaCarga = :fechaCarga")})
public class CrrReclamantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrrReclamantesPK crrReclamantesPK;
    @Column(name = "tiporeclamante")
    private String tiporeclamante;
    @Column(name = "fecha_raclamo")
    private String fechaRaclamo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "foto")
    private String foto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estadoreclamo")
    private String estadoreclamo;
    @Column(name = "subrogacion")
    private String subrogacion;
    @Column(name = "idaseguradorasub")
    private Integer idaseguradorasub;
    @Column(name = "tipopago")
    private String tipopago;
    @Column(name = "taller")
    private String taller;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "inicial")
    private String inicial;
    @Column(name = "tomo")
    private String tomo;
    @Column(name = "folio")
    private String folio;
    @Column(name = "asiento")
    private String asiento;
    @Column(name = "cedularuccorrida")
    private String cedularuccorrida;
    @Column(name = "tipopersona")
    private String tipopersona;
    @Column(name = "primernombre")
    private String primernombre;
    @Column(name = "segundonombre")
    private String segundonombre;
    @Column(name = "primerapellido")
    private String primerapellido;
    @Column(name = "segundoapelliddo")
    private String segundoapelliddo;
    @Column(name = "apellidocasada")
    private String apellidocasada;
    @Column(name = "codcliente")
    private String codcliente;
    @Column(name = "numplaca")
    private String numplaca;
    @Column(name = "numchasis")
    private String numchasis;
    @Column(name = "nummotor")
    private String nummotor;
    @Column(name = "vin")
    private String vin;
    @Column(name = "cupo")
    private String cupo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anno")
    private String anno;
    @Column(name = "color")
    private String color;
    @Column(name = "estilo")
    private String estilo;
    @Column(name = "hipoteca")
    private String hipoteca;
    @Column(name = "agenciahipotecaria")
    private String agenciahipotecaria;
    @Column(name = "perdidatotal")
    private String perdidatotal;
    @Column(name = "estadoauto")
    private String estadoauto;
    @Column(name = "provinciaconductor")
    private String provinciaconductor;
    @Column(name = "inicialconductor")
    private String inicialconductor;
    @Column(name = "tomoconductor")
    private String tomoconductor;
    @Column(name = "folioconductor")
    private String folioconductor;
    @Column(name = "cedulacorridaconductor")
    private String cedulacorridaconductor;
    @Column(name = "primernombreconductor")
    private String primernombreconductor;
    @Column(name = "segundonombreconductor")
    private String segundonombreconductor;
    @Column(name = "primerapellidoconductor")
    private String primerapellidoconductor;
    @Column(name = "segundoapellidoconductor")
    private String segundoapellidoconductor;
    @Column(name = "apellidocasadaconductor")
    private String apellidocasadaconductor;
    @Column(name = "fecha_carga")
    private String fechaCarga;
    @JoinColumn(name = "codaseguradora", referencedColumnName = "Aseguradora_id")
    @ManyToOne
    private Aseguradoras codaseguradora;
    @JoinColumn(name = "numeroreclamo", referencedColumnName = "numeroreclamo", insertable = false, updatable = false)
    @ManyToOne
    private CrrReclamo crrReclamo;

    public CrrReclamantes() {
    }

    public CrrReclamantes(CrrReclamantesPK crrReclamantesPK) {
        this.crrReclamantesPK = crrReclamantesPK;
    }

    public CrrReclamantes(String numeroreclamo, Integer numeroreclamante) {
        this.crrReclamantesPK = new CrrReclamantesPK(numeroreclamo, numeroreclamante);
    }

    public CrrReclamantesPK getCrrReclamantesPK() {
        return crrReclamantesPK;
    }

    public void setCrrReclamantesPK(CrrReclamantesPK crrReclamantesPK) {
        this.crrReclamantesPK = crrReclamantesPK;
    }

    public String getTiporeclamante() {
        return tiporeclamante;
    }

    public void setTiporeclamante(String tiporeclamante) {
        this.tiporeclamante = tiporeclamante;
    }

    public String getFechaRaclamo() {
        return fechaRaclamo;
    }

    public void setFechaRaclamo(String fechaRaclamo) {
        this.fechaRaclamo = fechaRaclamo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstadoreclamo() {
        return estadoreclamo;
    }

    public void setEstadoreclamo(String estadoreclamo) {
        this.estadoreclamo = estadoreclamo;
    }

    public String getSubrogacion() {
        return subrogacion;
    }

    public void setSubrogacion(String subrogacion) {
        this.subrogacion = subrogacion;
    }

    public Integer getIdaseguradorasub() {
        return idaseguradorasub;
    }

    public void setIdaseguradorasub(Integer idaseguradorasub) {
        this.idaseguradorasub = idaseguradorasub;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getCedularuccorrida() {
        return cedularuccorrida;
    }

    public void setCedularuccorrida(String cedularuccorrida) {
        this.cedularuccorrida = cedularuccorrida;
    }

    public String getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(String tipopersona) {
        this.tipopersona = tipopersona;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapelliddo() {
        return segundoapelliddo;
    }

    public void setSegundoapelliddo(String segundoapelliddo) {
        this.segundoapelliddo = segundoapelliddo;
    }

    public String getApellidocasada() {
        return apellidocasada;
    }

    public void setApellidocasada(String apellidocasada) {
        this.apellidocasada = apellidocasada;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public String getNumplaca() {
        return numplaca;
    }

    public void setNumplaca(String numplaca) {
        this.numplaca = numplaca;
    }

    public String getNumchasis() {
        return numchasis;
    }

    public void setNumchasis(String numchasis) {
        this.numchasis = numchasis;
    }

    public String getNummotor() {
        return nummotor;
    }

    public void setNummotor(String nummotor) {
        this.nummotor = nummotor;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(String hipoteca) {
        this.hipoteca = hipoteca;
    }

    public String getAgenciahipotecaria() {
        return agenciahipotecaria;
    }

    public void setAgenciahipotecaria(String agenciahipotecaria) {
        this.agenciahipotecaria = agenciahipotecaria;
    }

    public String getPerdidatotal() {
        return perdidatotal;
    }

    public void setPerdidatotal(String perdidatotal) {
        this.perdidatotal = perdidatotal;
    }

    public String getEstadoauto() {
        return estadoauto;
    }

    public void setEstadoauto(String estadoauto) {
        this.estadoauto = estadoauto;
    }

    public String getProvinciaconductor() {
        return provinciaconductor;
    }

    public void setProvinciaconductor(String provinciaconductor) {
        this.provinciaconductor = provinciaconductor;
    }

    public String getInicialconductor() {
        return inicialconductor;
    }

    public void setInicialconductor(String inicialconductor) {
        this.inicialconductor = inicialconductor;
    }

    public String getTomoconductor() {
        return tomoconductor;
    }

    public void setTomoconductor(String tomoconductor) {
        this.tomoconductor = tomoconductor;
    }

    public String getFolioconductor() {
        return folioconductor;
    }

    public void setFolioconductor(String folioconductor) {
        this.folioconductor = folioconductor;
    }

    public String getCedulacorridaconductor() {
        return cedulacorridaconductor;
    }

    public void setCedulacorridaconductor(String cedulacorridaconductor) {
        this.cedulacorridaconductor = cedulacorridaconductor;
    }

    public String getPrimernombreconductor() {
        return primernombreconductor;
    }

    public void setPrimernombreconductor(String primernombreconductor) {
        this.primernombreconductor = primernombreconductor;
    }

    public String getSegundonombreconductor() {
        return segundonombreconductor;
    }

    public void setSegundonombreconductor(String segundonombreconductor) {
        this.segundonombreconductor = segundonombreconductor;
    }

    public String getPrimerapellidoconductor() {
        return primerapellidoconductor;
    }

    public void setPrimerapellidoconductor(String primerapellidoconductor) {
        this.primerapellidoconductor = primerapellidoconductor;
    }

    public String getSegundoapellidoconductor() {
        return segundoapellidoconductor;
    }

    public void setSegundoapellidoconductor(String segundoapellidoconductor) {
        this.segundoapellidoconductor = segundoapellidoconductor;
    }

    public String getApellidocasadaconductor() {
        return apellidocasadaconductor;
    }

    public void setApellidocasadaconductor(String apellidocasadaconductor) {
        this.apellidocasadaconductor = apellidocasadaconductor;
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

    public CrrReclamo getCrrReclamo() {
        return crrReclamo;
    }

    public void setCrrReclamo(CrrReclamo crrReclamo) {
        this.crrReclamo = crrReclamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crrReclamantesPK != null ? crrReclamantesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrrReclamantes)) {
            return false;
        }
        CrrReclamantes other = (CrrReclamantes) object;
        if ((this.crrReclamantesPK == null && other.crrReclamantesPK != null) || (this.crrReclamantesPK != null && !this.crrReclamantesPK.equals(other.crrReclamantesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.CrrReclamantes[ crrReclamantesPK=" + crrReclamantesPK + " ]";
    }
    
}
