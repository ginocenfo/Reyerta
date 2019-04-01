package com.charactergenerator.reyerta.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.charactergenerator.reyerta.domain.enumeration.IncrustacionesArmadura;

/**
 * A Armadura.
 */
@Entity
@Table(name = "armadura")
public class Armadura implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "evasion_bonus")
    private Integer evasionBonus;

    @Column(name = "max_dex")
    private Integer maxDex;

    @Column(name = "jhi_level")
    private Integer level;

    @Column(name = "uc")
    private Integer uc;

    @Column(name = "ppa")
    private Integer ppa;

    @Column(name = "cast_fail_chance")
    private Integer castFailChance;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "incrustacion_armadura")
    private IncrustacionesArmadura incrustacionArmadura;

    @OneToOne
    @JoinColumn(unique = true)
    private Dado damageByShield;

    @ManyToOne
    @JsonIgnoreProperties("armaduras")
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

    public Armadura type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Armadura name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEvasionBonus() {
        return evasionBonus;
    }

    public Armadura evasionBonus(Integer evasionBonus) {
        this.evasionBonus = evasionBonus;
        return this;
    }

    public void setEvasionBonus(Integer evasionBonus) {
        this.evasionBonus = evasionBonus;
    }

    public Integer getMaxDex() {
        return maxDex;
    }

    public Armadura maxDex(Integer maxDex) {
        this.maxDex = maxDex;
        return this;
    }

    public void setMaxDex(Integer maxDex) {
        this.maxDex = maxDex;
    }

    public Integer getLevel() {
        return level;
    }

    public Armadura level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUc() {
        return uc;
    }

    public Armadura uc(Integer uc) {
        this.uc = uc;
        return this;
    }

    public void setUc(Integer uc) {
        this.uc = uc;
    }

    public Integer getPpa() {
        return ppa;
    }

    public Armadura ppa(Integer ppa) {
        this.ppa = ppa;
        return this;
    }

    public void setPpa(Integer ppa) {
        this.ppa = ppa;
    }

    public Integer getCastFailChance() {
        return castFailChance;
    }

    public Armadura castFailChance(Integer castFailChance) {
        this.castFailChance = castFailChance;
        return this;
    }

    public void setCastFailChance(Integer castFailChance) {
        this.castFailChance = castFailChance;
    }

    public Double getPrice() {
        return price;
    }

    public Armadura price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public IncrustacionesArmadura getIncrustacionArmadura() {
        return incrustacionArmadura;
    }

    public Armadura incrustacionArmadura(IncrustacionesArmadura incrustacionArmadura) {
        this.incrustacionArmadura = incrustacionArmadura;
        return this;
    }

    public void setIncrustacionArmadura(IncrustacionesArmadura incrustacionArmadura) {
        this.incrustacionArmadura = incrustacionArmadura;
    }

    public Dado getDamageByShield() {
        return damageByShield;
    }

    public Armadura damageByShield(Dado dado) {
        this.damageByShield = dado;
        return this;
    }

    public void setDamageByShield(Dado dado) {
        this.damageByShield = dado;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public Armadura personaje(Personaje personaje) {
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
        Armadura armadura = (Armadura) o;
        if (armadura.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), armadura.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Armadura{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", evasionBonus=" + getEvasionBonus() +
            ", maxDex=" + getMaxDex() +
            ", level=" + getLevel() +
            ", uc=" + getUc() +
            ", ppa=" + getPpa() +
            ", castFailChance=" + getCastFailChance() +
            ", price=" + getPrice() +
            ", incrustacionArmadura='" + getIncrustacionArmadura() + "'" +
            "}";
    }
}
