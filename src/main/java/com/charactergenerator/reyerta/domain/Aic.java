package com.charactergenerator.reyerta.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Aic.
 */
@Entity
@Table(name = "aic")
public class Aic implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prob")
    private Integer prob;

    @Column(name = "mult")
    private Integer mult;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProb() {
        return prob;
    }

    public Aic prob(Integer prob) {
        this.prob = prob;
        return this;
    }

    public void setProb(Integer prob) {
        this.prob = prob;
    }

    public Integer getMult() {
        return mult;
    }

    public Aic mult(Integer mult) {
        this.mult = mult;
        return this;
    }

    public void setMult(Integer mult) {
        this.mult = mult;
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
        Aic aic = (Aic) o;
        if (aic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aic{" +
            "id=" + getId() +
            ", prob=" + getProb() +
            ", mult=" + getMult() +
            "}";
    }
}
