package com.charactergenerator.reyerta.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.charactergenerator.reyerta.domain.enumeration.Profile;

/**
 * A ProfilePoints.
 */
@Entity
@Table(name = "profile_points")
public class ProfilePoints implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "points")
    private Integer points;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil")
    private Profile perfil;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public ProfilePoints points(Integer points) {
        this.points = points;
        return this;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Profile getPerfil() {
        return perfil;
    }

    public ProfilePoints perfil(Profile perfil) {
        this.perfil = perfil;
        return this;
    }

    public void setPerfil(Profile perfil) {
        this.perfil = perfil;
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
        ProfilePoints profilePoints = (ProfilePoints) o;
        if (profilePoints.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profilePoints.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfilePoints{" +
            "id=" + getId() +
            ", points=" + getPoints() +
            ", perfil='" + getPerfil() + "'" +
            "}";
    }
}
