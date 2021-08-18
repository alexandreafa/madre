package br.com.basis.madre.madreexames.service.dto;

import java.time.Instant;
import java.time.Duration;
import javax.validation.constraints.*;
import java.io.Serializable;
import br.com.basis.madre.madreexames.domain.enumeration.Dia;

/**
 * A DTO for the {@link br.com.basis.madre.madreexames.domain.HorarioAgendado} entity.
 */
public class HorarioAgendadoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant horaInicio;

    @NotNull
    private Instant horafim;

    private Integer numeroDeHorarios;

    @NotNull
    private Dia dia;

    private Duration duracao;

    @NotNull
    private Boolean ativo;

    @NotNull
    private Boolean exclusivo;


    private Long horarioAgendadoId;

    private String horarioAgendadoDia;

    private Long gradeDeAgendamentoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Instant horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Instant getHorafim() {
        return horafim;
    }

    public void setHorafim(Instant horafim) {
        this.horafim = horafim;
    }

    public Integer getNumeroDeHorarios() {
        return numeroDeHorarios;
    }

    public void setNumeroDeHorarios(Integer numeroDeHorarios) {
        this.numeroDeHorarios = numeroDeHorarios;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean isExclusivo() {
        return exclusivo;
    }

    public void setExclusivo(Boolean exclusivo) {
        this.exclusivo = exclusivo;
    }

    public Long getHorarioAgendadoId() {
        return horarioAgendadoId;
    }

    public void setHorarioAgendadoId(Long tipoDeMarcacaoId) {
        this.horarioAgendadoId = tipoDeMarcacaoId;
    }

    public String getHorarioAgendadoDia() {
        return horarioAgendadoDia;
    }

    public void setHorarioAgendadoDia(String tipoDeMarcacaoDia) {
        this.horarioAgendadoDia = tipoDeMarcacaoDia;
    }

    public Long getGradeDeAgendamentoId() {
        return gradeDeAgendamentoId;
    }

    public void setGradeDeAgendamentoId(Long gradeDeAgendamentoId) {
        this.gradeDeAgendamentoId = gradeDeAgendamentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HorarioAgendadoDTO)) {
            return false;
        }

        return id != null && id.equals(((HorarioAgendadoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HorarioAgendadoDTO{" +
            "id=" + getId() +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horafim='" + getHorafim() + "'" +
            ", numeroDeHorarios=" + getNumeroDeHorarios() +
            ", dia='" + getDia() + "'" +
            ", duracao='" + getDuracao() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", exclusivo='" + isExclusivo() + "'" +
            ", horarioAgendadoId=" + getHorarioAgendadoId() +
            ", horarioAgendadoDia='" + getHorarioAgendadoDia() + "'" +
            ", gradeDeAgendamentoId=" + getGradeDeAgendamentoId() +
            "}";
    }
}
