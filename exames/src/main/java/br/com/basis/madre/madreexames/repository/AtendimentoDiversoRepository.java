package br.com.basis.madre.madreexames.repository;

import br.com.basis.madre.madreexames.domain.AtendimentoDiverso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AtendimentoDiverso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtendimentoDiversoRepository extends JpaRepository<AtendimentoDiverso, Long> {
}
