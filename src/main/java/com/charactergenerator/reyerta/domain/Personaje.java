package com.charactergenerator.reyerta.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.charactergenerator.reyerta.domain.enumeration.Alignment;

import com.charactergenerator.reyerta.domain.enumeration.Dexterity;

import com.charactergenerator.reyerta.domain.enumeration.Sizes;

import com.charactergenerator.reyerta.domain.enumeration.Races;

import com.charactergenerator.reyerta.domain.enumeration.CharacterClase;

/**
 * A Personaje.
 */
@Entity
@Table(name = "personaje")
public class Personaje implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "character_name")
    private String characterName;

    @Enumerated(EnumType.STRING)
    @Column(name = "alignment")
    private Alignment alignment;

    @Column(name = "religion")
    private String religion;

    @Column(name = "gender")
    private String gender;

    @Column(name = "real_age")
    private Integer realAge;

    @Column(name = "apparent_age")
    private Integer apparentAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "dexterity")
    private Dexterity dexterity;

    @Column(name = "origin_name")
    private String originName;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_size")
    private Sizes size;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "hair_color")
    private String hairColor;

    @Column(name = "tez_color")
    private String tezColor;

    @Column(name = "max_hit_points")
    private Integer maxHitPoints;

    @Column(name = "current_hit_points")
    private Integer currentHitPoints;

    @Enumerated(EnumType.STRING)
    @Column(name = "race")
    private Races race;

    @Enumerated(EnumType.STRING)
    @Column(name = "character_class")
    private CharacterClase characterClass;

    @OneToMany(mappedBy = "personaje")
    private Set<Arma> armas = new HashSet<>();
    @OneToMany(mappedBy = "personaje")
    private Set<Armadura> armaduras = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Personaje ownerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Personaje characterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Personaje alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public String getReligion() {
        return religion;
    }

    public Personaje religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public Personaje gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRealAge() {
        return realAge;
    }

    public Personaje realAge(Integer realAge) {
        this.realAge = realAge;
        return this;
    }

    public void setRealAge(Integer realAge) {
        this.realAge = realAge;
    }

    public Integer getApparentAge() {
        return apparentAge;
    }

    public Personaje apparentAge(Integer apparentAge) {
        this.apparentAge = apparentAge;
        return this;
    }

    public void setApparentAge(Integer apparentAge) {
        this.apparentAge = apparentAge;
    }

    public Dexterity getDexterity() {
        return dexterity;
    }

    public Personaje dexterity(Dexterity dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public void setDexterity(Dexterity dexterity) {
        this.dexterity = dexterity;
    }

    public String getOriginName() {
        return originName;
    }

    public Personaje originName(String originName) {
        this.originName = originName;
        return this;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Sizes getSize() {
        return size;
    }

    public Personaje size(Sizes size) {
        this.size = size;
        return this;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public Double getHeight() {
        return height;
    }

    public Personaje height(Double height) {
        this.height = height;
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public Personaje weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public Personaje eyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public Personaje hairColor(String hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getTezColor() {
        return tezColor;
    }

    public Personaje tezColor(String tezColor) {
        this.tezColor = tezColor;
        return this;
    }

    public void setTezColor(String tezColor) {
        this.tezColor = tezColor;
    }

    public Integer getMaxHitPoints() {
        return maxHitPoints;
    }

    public Personaje maxHitPoints(Integer maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        return this;
    }

    public void setMaxHitPoints(Integer maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public Personaje currentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
        return this;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public Races getRace() {
        return race;
    }

    public Personaje race(Races race) {
        this.race = race;
        return this;
    }

    public void setRace(Races race) {
        this.race = race;
    }

    public CharacterClase getCharacterClass() {
        return characterClass;
    }

    public Personaje characterClass(CharacterClase characterClass) {
        this.characterClass = characterClass;
        return this;
    }

    public void setCharacterClass(CharacterClase characterClass) {
        this.characterClass = characterClass;
    }

    public Set<Arma> getArmas() {
        return armas;
    }

    public Personaje armas(Set<Arma> armas) {
        this.armas = armas;
        return this;
    }

    public Personaje addArmas(Arma arma) {
        this.armas.add(arma);
        arma.setPersonaje(this);
        return this;
    }

    public Personaje removeArmas(Arma arma) {
        this.armas.remove(arma);
        arma.setPersonaje(null);
        return this;
    }

    public void setArmas(Set<Arma> armas) {
        this.armas = armas;
    }

    public Set<Armadura> getArmaduras() {
        return armaduras;
    }

    public Personaje armaduras(Set<Armadura> armaduras) {
        this.armaduras = armaduras;
        return this;
    }

    public Personaje addArmadura(Armadura armadura) {
        this.armaduras.add(armadura);
        armadura.setPersonaje(this);
        return this;
    }

    public Personaje removeArmadura(Armadura armadura) {
        this.armaduras.remove(armadura);
        armadura.setPersonaje(null);
        return this;
    }

    public void setArmaduras(Set<Armadura> armaduras) {
        this.armaduras = armaduras;
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
        Personaje personaje = (Personaje) o;
        if (personaje.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personaje.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Personaje{" +
            "id=" + getId() +
            ", ownerName='" + getOwnerName() + "'" +
            ", characterName='" + getCharacterName() + "'" +
            ", alignment='" + getAlignment() + "'" +
            ", religion='" + getReligion() + "'" +
            ", gender='" + getGender() + "'" +
            ", realAge=" + getRealAge() +
            ", apparentAge=" + getApparentAge() +
            ", dexterity='" + getDexterity() + "'" +
            ", originName='" + getOriginName() + "'" +
            ", size='" + getSize() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", eyeColor='" + getEyeColor() + "'" +
            ", hairColor='" + getHairColor() + "'" +
            ", tezColor='" + getTezColor() + "'" +
            ", maxHitPoints=" + getMaxHitPoints() +
            ", currentHitPoints=" + getCurrentHitPoints() +
            ", race='" + getRace() + "'" +
            ", characterClass='" + getCharacterClass() + "'" +
            "}";
    }
}
