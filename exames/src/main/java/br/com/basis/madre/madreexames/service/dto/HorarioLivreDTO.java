package br.com.basis.madre.madreexames.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link br.com.basis.madre.madreexames.domain.HorarioLivre} entity.
 */
public class HorarioLivreDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;

    @NotNull
    private Boolean ocupado;


    private Long horarioAgendadoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Long getHorarioAgendadoId() {
        return horarioAgendadoId;
    }

    public void setHorarioAgendadoId(Long horarioAgendadoId) {
        this.horarioAgendadoId = horarioAgendadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HorarioLivreDTO)) {
            return false;
        }

        return id != null && id.equals(((HorarioLivreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HorarioLivreDTO{" +
            "id=" + getId() +
            ", dataInicial='" + getDataInicial() + "'" +
            ", dataFinal='" + getDataFinal() + "'" +
            ", ocupado='" + isOcupado() + "'" +
            ", horarioAgendadoId=" + getHorarioAgendadoId() +
            "}";
    }
}
