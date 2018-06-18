package br.com.basis.madre.cadastros.repository;

import br.com.basis.madre.cadastros.domain.TipoPergunta;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TipoPergunta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoPerguntaRepository extends JpaRepository<TipoPergunta, Long> {

}
