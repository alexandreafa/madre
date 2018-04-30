package br.com.basis.madre.cadastros.repository;


import br.com.basis.madre.cadastros.domain.UnidadeHospitalar;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UnidadeHospitalar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnidadeHospitalarRepository extends JpaRepository<UnidadeHospitalar, Long> {
	Optional<UnidadeHospitalar> findOneByCnpj(String cnpj);

}
