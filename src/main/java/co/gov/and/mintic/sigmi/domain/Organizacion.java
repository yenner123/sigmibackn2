package co.gov.and.mintic.sigmi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Organizacion.
 */
@Entity
@Table(name = "adm_organizacion")
public class Organizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nit", length = 50, nullable = false)
    private String nit;

    @NotNull
    @Size(max = 120)
    @Column(name = "razon_social", length = 120, nullable = false)
    private String razonSocial;

    @Size(max = 120)
    @Column(name = "sigla", length = 120, unique = true)
    private String sigla;

    @Size(max = 254)
    @Column(name = "pagina_web", length = 254)
    private String paginaWeb;

    @Size(max = 254)
    @Column(name = "email", length = 254)
    private String email;

    @Size(max = 254)
    @Column(name = "direccion", length = 254)
    private String direccion;

    @Size(max = 50)
    @Column(name = "telefono", length = 50)
    private String telefono;

    @Size(max = 50)
    @Column(name = "fax", length = 50)
    private String fax;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private Municipio municipio;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private NaturalezaJuridica naturalezaJuridica;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private ClasificacionOrganica clasificacionOrganica;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private Nivel nivel;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private Orden orden;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private SubOrden subOrden;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private Sector sector;

    @ManyToOne
    @JsonIgnoreProperties(value = "organizacions", allowSetters = true)
    private Organizacion padre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public Organizacion nit(String nit) {
        this.nit = nit;
        return this;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public Organizacion razonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSigla() {
        return sigla;
    }

    public Organizacion sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public Organizacion paginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
        return this;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getEmail() {
        return email;
    }

    public Organizacion email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public Organizacion direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Organizacion telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public Organizacion fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public Organizacion fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Organizacion fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public Organizacion estado(Integer estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public Organizacion municipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public NaturalezaJuridica getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public Organizacion naturalezaJuridica(NaturalezaJuridica naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
        return this;
    }

    public void setNaturalezaJuridica(NaturalezaJuridica naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    public ClasificacionOrganica getClasificacionOrganica() {
        return clasificacionOrganica;
    }

    public Organizacion clasificacionOrganica(ClasificacionOrganica clasificacionOrganica) {
        this.clasificacionOrganica = clasificacionOrganica;
        return this;
    }

    public void setClasificacionOrganica(ClasificacionOrganica clasificacionOrganica) {
        this.clasificacionOrganica = clasificacionOrganica;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public Organizacion nivel(Nivel nivel) {
        this.nivel = nivel;
        return this;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Orden getOrden() {
        return orden;
    }

    public Organizacion orden(Orden orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public SubOrden getSubOrden() {
        return subOrden;
    }

    public Organizacion subOrden(SubOrden subOrden) {
        this.subOrden = subOrden;
        return this;
    }

    public void setSubOrden(SubOrden subOrden) {
        this.subOrden = subOrden;
    }

    public Sector getSector() {
        return sector;
    }

    public Organizacion sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Organizacion getPadre() {
        return padre;
    }

    public Organizacion padre(Organizacion organizacion) {
        this.padre = organizacion;
        return this;
    }

    public void setPadre(Organizacion organizacion) {
        this.padre = organizacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organizacion)) {
            return false;
        }
        return id != null && id.equals(((Organizacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organizacion{" +
            "id=" + getId() +
            ", nit='" + getNit() + "'" +
            ", razonSocial='" + getRazonSocial() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", paginaWeb='" + getPaginaWeb() + "'" +
            ", email='" + getEmail() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", fax='" + getFax() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaActualizacion='" + getFechaActualizacion() + "'" +
            ", estado=" + getEstado() +
            "}";
    }
}
