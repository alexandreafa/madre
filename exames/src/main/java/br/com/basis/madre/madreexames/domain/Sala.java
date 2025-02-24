package br.com.basis.madre.madreexames.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Sala.
 */
@Entity
@Table(name = "sala")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "madre-exames-sala")
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSala")
    @SequenceGenerator(name = "seqSala")
    private Long id;

    @NotNull
    @Column(name = "identificacao_da_sala", nullable = false)
    private String identificacaoDaSala;

    @NotNull
    @Column(name = "locacao_da_sala", nullable = false)
    private String locacaoDaSala;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "unidade_executora_id")
    private Integer unidadeExecutoraId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacaoDaSala() {
        return identificacaoDaSala;
    }

    public Sala identificacaoDaSala(String identificacaoDaSala) {
        this.identificacaoDaSala = identificacaoDaSala;
        return this;
    }

    public void setIdentificacaoDaSala(String identificacaoDaSala) {
        this.identificacaoDaSala = identificacaoDaSala;
    }

    public String getLocacaoDaSala() {
        return locacaoDaSala;
    }

    public Sala locacaoDaSala(String locacaoDaSala) {
        this.locacaoDaSala = locacaoDaSala;
        return this;
    }

    public void setLocacaoDaSala(String locacaoDaSala) {
        this.locacaoDaSala = locacaoDaSala;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Sala ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getUnidadeExecutoraId() {
        return unidadeExecutoraId;
    }

    public Sala unidadeExecutoraId(Integer unidadeExecutoraId) {
        this.unidadeExecutoraId = unidadeExecutoraId;
        return this;
    }

    public void setUnidadeExecutoraId(Integer unidadeExecutoraId) {
        this.unidadeExecutoraId = unidadeExecutoraId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sala)) {
            return false;
        }
        return id != null && id.equals(((Sala) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sala{" +
            "id=" + getId() +
            ", identificacaoDaSala='" + getIdentificacaoDaSala() + "'" +
            ", locacaoDaSala='" + getLocacaoDaSala() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", unidadeExecutoraId=" + getUnidadeExecutoraId() +
            "}";
    }
}
