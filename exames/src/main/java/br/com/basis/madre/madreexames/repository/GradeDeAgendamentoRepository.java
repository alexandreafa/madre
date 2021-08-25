package br.com.basis.madre.madreexames.repository;

import br.com.basis.madre.madreexames.domain.GradeDeAgendamento;

import br.com.basis.madre.madreexames.service.dto.GradeDeAgendamentoDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.Optional;

/**
 * Spring Data  repository for the GradeDeAgendamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GradeDeAgendamentoRepository extends JpaRepository<GradeDeAgendamento, Long> {

    @Query("select new br.com.basis.madre.madreexames.service.dto.GradeDeAgendamentoDTO(g.id, g.unidadeExecutoraId, g.responsavelId, g.ativo, e.id, e.nome, s.id, s.identificacaoDaSala, gp.id) from GradeDeAgendamento g join g.exameGrade e join g.salaGrade s join g.grupoAgendamentoExame gp where g.id = :id")
    GradeDeAgendamentoDTO buscaPorId(@Param("id") Long id);

}
