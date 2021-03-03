package co.gov.and.mintic.sigmi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ClasificacionOrganica.
 */
@Entity
@Table(name = "adm_clasificacion_organica")
public class ClasificacionOrganica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Size(min = 3, max = 3)
    @Column(name = "sigla", length = 3, unique = true)
    private String sigla;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Integer estado;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ClasificacionOrganica nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public ClasificacionOrganica sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public ClasificacionOrganica fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public ClasificacionOrganica estado(Integer estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClasificacionOrganica)) {
            return false;
        }
        return id != null && id.equals(((ClasificacionOrganica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClasificacionOrganica{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", estado=" + getEstado() +
            "}";
    }
}
