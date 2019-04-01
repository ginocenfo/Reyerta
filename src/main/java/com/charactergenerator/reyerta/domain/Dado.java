package com.charactergenerator.reyerta.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Dado.
 */
@Entity
@Table(name = "dado")
public class Dado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_size")
    private Integer size;

    @Column(name = "cant")
    private Integer cant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public Dado size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCant() {
        return cant;
    }

    public Dado cant(Integer cant) {
        this.cant = cant;
        return this;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dado dado = (Dado) o;
        if (dado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dado{" +
            "id=" + getId() +
            ", size=" + getSize() +
            ", cant=" + getCant() +
            "}";
    }
}
