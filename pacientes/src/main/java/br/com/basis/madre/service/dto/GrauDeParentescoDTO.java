package br.com.basis.madre.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.basis.madre.domain.GrauDeParentesco} entity.
 */
public class GrauDeParentescoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String valor;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrauDeParentescoDTO grauDeParentescoDTO = (GrauDeParentescoDTO) o;
        if (grauDeParentescoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grauDeParentescoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrauDeParentescoDTO{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
