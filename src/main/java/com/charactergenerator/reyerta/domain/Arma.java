package com.charactergenerator.reyerta.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.charactergenerator.reyerta.domain.enumeration.DamageType;

import com.charactergenerator.reyerta.domain.enumeration.Special;

import com.charactergenerator.reyerta.domain.enumeration.IncrustacionesArma;

/**
 * A Arma.
 */
@Entity
@Table(name = "arma")
public class Arma implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "prize")
    private Double prize;

    @Column(name = "jhi_level")
    private Integer level;

    @Column(name = "is_masterpiece")
    private Boolean isMasterpiece;

    @Column(name = "elite_level")
    private Integer eliteLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "damage_type")
    private DamageType damageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "special")
    private Special special;

    @Enumerated(EnumType.STRING)
    @Column(name = "incrustacion")
    private IncrustacionesArma incrustacion;

    @OneToOne
    @JoinColumn(unique = true)
    private Dado dado;

    @OneToOne
    @JoinColumn(unique = true)
    private Aic crit;

    @ManyToOne
    @JsonIgnoreProperties("armas")
    private Personaje personaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Arma type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Arma name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrize() {
        return prize;
    }

    public Arma prize(Double prize) {
        this.prize = prize;
        return this;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Integer getLevel() {
        return level;
    }

    public Arma level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean isIsMasterpiece() {
        return isMasterpiece;
    }

    public Arma isMasterpiece(Boolean isMasterpiece) {
        this.isMasterpiece = isMasterpiece;
        return this;
    }

    public void setIsMasterpiece(Boolean isMasterpiece) {
        this.isMasterpiece = isMasterpiece;
    }

    public Integer getEliteLevel() {
        return eliteLevel;
    }

    public Arma eliteLevel(Integer eliteLevel) {
        this.eliteLevel = eliteLevel;
        return this;
    }

    public void setEliteLevel(Integer eliteLevel) {
        this.eliteLevel = eliteLevel;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public Arma damageType(DamageType damageType) {
        this.damageType = damageType;
        return this;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Special getSpecial() {
        return special;
    }

    public Arma special(Special special) {
        this.special = special;
        return this;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }

    public IncrustacionesArma getIncrustacion() {
        return incrustacion;
    }

    public Arma incrustacion(IncrustacionesArma incrustacion) {
        this.incrustacion = incrustacion;
        return this;
    }

    public void setIncrustacion(IncrustacionesArma incrustacion) {
        this.incrustacion = incrustacion;
    }

    public Dado getDado() {
        return dado;
    }

    public Arma dado(Dado dado) {
        this.dado = dado;
        return this;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Aic getCrit() {
        return crit;
    }

    public Arma crit(Aic aic) {
        this.crit = aic;
        return this;
    }

    public void setCrit(Aic aic) {
        this.crit = aic;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public Arma personaje(Personaje personaje) {
        this.personaje = personaje;
        return this;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
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
        Arma arma = (Arma) o;
        if (arma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), arma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Arma{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", prize=" + getPrize() +
            ", level=" + getLevel() +
            ", isMasterpiece='" + isIsMasterpiece() + "'" +
            ", eliteLevel=" + getEliteLevel() +
            ", damageType='" + getDamageType() + "'" +
            ", special='" + getSpecial() + "'" +
            ", incrustacion='" + getIncrustacion() + "'" +
            "}";
    }
}
