package com.charactergenerator.reyerta.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.charactergenerator.reyerta.domain.enumeration.Profile;

/**
 * A Skill.
 */
@Entity
@Table(name = "skill")
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "related_profile")
    private Profile relatedProfile;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "magic_object")
    private Integer magicObject;

    @Column(name = "other")
    private Integer other;

    @Column(name = "penalizer")
    private Integer penalizer;

    @Column(name = "is_trained")
    private Boolean isTrained;

    @Column(name = "is_expertise")
    private Boolean isExpertise;

    @Column(name = "is_penalized")
    private Boolean isPenalized;

    @Column(name = "is_penalized_by_language")
    private Boolean isPenalizedByLanguage;

    @Column(name = "girso_pieces")
    private Integer girsoPieces;

    @Column(name = "platinum_pieces")
    private Integer platinumPieces;

    @Column(name = "gold_pieces")
    private Integer goldPieces;

    @Column(name = "silver_pieces")
    private Integer silverPieces;

    @Column(name = "copper_pieces")
    private Integer copperPieces;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Skill name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getRelatedProfile() {
        return relatedProfile;
    }

    public Skill relatedProfile(Profile relatedProfile) {
        this.relatedProfile = relatedProfile;
        return this;
    }

    public void setRelatedProfile(Profile relatedProfile) {
        this.relatedProfile = relatedProfile;
    }

    public Integer getGrade() {
        return grade;
    }

    public Skill grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getMagicObject() {
        return magicObject;
    }

    public Skill magicObject(Integer magicObject) {
        this.magicObject = magicObject;
        return this;
    }

    public void setMagicObject(Integer magicObject) {
        this.magicObject = magicObject;
    }

    public Integer getOther() {
        return other;
    }

    public Skill other(Integer other) {
        this.other = other;
        return this;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getPenalizer() {
        return penalizer;
    }

    public Skill penalizer(Integer penalizer) {
        this.penalizer = penalizer;
        return this;
    }

    public void setPenalizer(Integer penalizer) {
        this.penalizer = penalizer;
    }

    public Boolean isIsTrained() {
        return isTrained;
    }

    public Skill isTrained(Boolean isTrained) {
        this.isTrained = isTrained;
        return this;
    }

    public void setIsTrained(Boolean isTrained) {
        this.isTrained = isTrained;
    }

    public Boolean isIsExpertise() {
        return isExpertise;
    }

    public Skill isExpertise(Boolean isExpertise) {
        this.isExpertise = isExpertise;
        return this;
    }

    public void setIsExpertise(Boolean isExpertise) {
        this.isExpertise = isExpertise;
    }

    public Boolean isIsPenalized() {
        return isPenalized;
    }

    public Skill isPenalized(Boolean isPenalized) {
        this.isPenalized = isPenalized;
        return this;
    }

    public void setIsPenalized(Boolean isPenalized) {
        this.isPenalized = isPenalized;
    }

    public Boolean isIsPenalizedByLanguage() {
        return isPenalizedByLanguage;
    }

    public Skill isPenalizedByLanguage(Boolean isPenalizedByLanguage) {
        this.isPenalizedByLanguage = isPenalizedByLanguage;
        return this;
    }

    public void setIsPenalizedByLanguage(Boolean isPenalizedByLanguage) {
        this.isPenalizedByLanguage = isPenalizedByLanguage;
    }

    public Integer getGirsoPieces() {
        return girsoPieces;
    }

    public Skill girsoPieces(Integer girsoPieces) {
        this.girsoPieces = girsoPieces;
        return this;
    }

    public void setGirsoPieces(Integer girsoPieces) {
        this.girsoPieces = girsoPieces;
    }

    public Integer getPlatinumPieces() {
        return platinumPieces;
    }

    public Skill platinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
        return this;
    }

    public void setPlatinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
    }

    public Integer getGoldPieces() {
        return goldPieces;
    }

    public Skill goldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
        return this;
    }

    public void setGoldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
    }

    public Integer getSilverPieces() {
        return silverPieces;
    }

    public Skill silverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
        return this;
    }

    public void setSilverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
    }

    public Integer getCopperPieces() {
        return copperPieces;
    }

    public Skill copperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
        return this;
    }

    public void setCopperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
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
        Skill skill = (Skill) o;
        if (skill.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Skill{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", relatedProfile='" + getRelatedProfile() + "'" +
            ", grade=" + getGrade() +
            ", magicObject=" + getMagicObject() +
            ", other=" + getOther() +
            ", penalizer=" + getPenalizer() +
            ", isTrained='" + isIsTrained() + "'" +
            ", isExpertise='" + isIsExpertise() + "'" +
            ", isPenalized='" + isIsPenalized() + "'" +
            ", isPenalizedByLanguage='" + isIsPenalizedByLanguage() + "'" +
            ", girsoPieces=" + getGirsoPieces() +
            ", platinumPieces=" + getPlatinumPieces() +
            ", goldPieces=" + getGoldPieces() +
            ", silverPieces=" + getSilverPieces() +
            ", copperPieces=" + getCopperPieces() +
            "}";
    }
}
