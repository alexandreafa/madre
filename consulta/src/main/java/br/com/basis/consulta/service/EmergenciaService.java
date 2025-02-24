package br.com.basis.consulta.service;

import br.com.basis.consulta.domain.Emergencia;
import br.com.basis.consulta.repository.EmergenciaRepository;
import br.com.basis.consulta.repository.search.EmergenciaSearchRepository;
import br.com.basis.consulta.service.dto.EmergenciaDTO;
import br.com.basis.consulta.service.mapper.EmergenciaMapper;
import br.com.basis.consulta.service.projection.CalendarioResumo;
import org.elasticsearch.common.Strings;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link Emergencia}.
 */
@Service
@Transactional
public class EmergenciaService {

    public static final String NUMERO_CONSULTA = "numeroConsulta";
    public static final String DATA_HORA_CONSULTA = "dataHoraDaConsulta";
    private final Logger log = LoggerFactory.getLogger(EmergenciaService.class);

    private final EmergenciaRepository emergenciaRepository;

    private final EmergenciaMapper emergenciaMapper;

    private final EmergenciaSearchRepository emergenciaSearchRepository;

    private final String[] includes = new String[]{"id", NUMERO_CONSULTA, DATA_HORA_CONSULTA, "grade", "numeroSala", "turno", "tipoPagador", "gradesDisponiveis", "clinicaCentralId", "justificativa", "observacoes", "pacienteId", "condicaoDeAtendimento", "formaDeAgendamento", "especialidade", "profissional"};

    public EmergenciaService(EmergenciaRepository emergenciaRepository, EmergenciaMapper emergenciaMapper, EmergenciaSearchRepository emergenciaSearchRepository) {
        this.emergenciaRepository = emergenciaRepository;
        this.emergenciaMapper = emergenciaMapper;
        this.emergenciaSearchRepository = emergenciaSearchRepository;
    }

    /**
     * Save a emergencia.
     *
     * @param emergenciaDTO the entity to save.
     * @return the persisted entity.
     */
    public EmergenciaDTO save(EmergenciaDTO emergenciaDTO) {
        log.debug("Request to save Emergencia : {}", emergenciaDTO);
        Emergencia emergencia = emergenciaMapper.toEntity(emergenciaDTO);
        emergencia = emergenciaRepository.save(emergencia);
        EmergenciaDTO result = emergenciaMapper.toDto(emergencia);
        emergenciaSearchRepository.save(emergencia);
        return result;
    }

    /**
     * Get all the emergencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmergenciaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Emergencias");
        return emergenciaRepository.findAll(pageable)
            .map(emergenciaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<EmergenciaDTO> buscarTodos(Pageable pageable) {
        log.debug("Request to get all Emergencias");
        EmergenciaService emergenciaSearchRepositoryRepository;
        return emergenciaSearchRepository.findAll(pageable)
            .map(emergenciaMapper::toDto);
    }

    /**
     * Get one emergencia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmergenciaDTO> findOne(Long id) {
        log.debug("Request to get Emergencia : {}", id);
        return emergenciaRepository.findById(id)
            .map(emergenciaMapper::toDto);
    }

    /**
     * Delete the emergencia by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Emergencia : {}", id);
        emergenciaRepository.deleteById(id);
        emergenciaSearchRepository.deleteById(id);
    }

    /**
     * Search for the emergencia corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmergenciaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Emergencias for query {}", query);
        return emergenciaSearchRepository.search(queryStringQuery(query), pageable)
            .map(emergenciaMapper::toDto);
    }

    public Page<CalendarioResumo> buscarCalendarioResumo(Pageable pageable) {
        return emergenciaRepository.findAllCalendarioResumoBy(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Emergencia> filtraConsultaEmergencial(Pageable pageable, String numeroConsulta, String grade, String pacienteId,String profissional, String especialidade, String clinicaCentral, String dataConsulta) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        filter(queryBuilder,"grade",grade);
        filter(queryBuilder, NUMERO_CONSULTA,numeroConsulta);
        filter(queryBuilder, "pacienteId", pacienteId);
        filter(queryBuilder, "profissional",especialidade);
        filter(queryBuilder, "especialidade",profissional);
        filter(queryBuilder ,"clinicaCentralId",clinicaCentral);
        filter(queryBuilder,DATA_HORA_CONSULTA,dataConsulta);
        SearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .withPageable(pageable)
            .build();
        return emergenciaSearchRepository.search(query);
    }

    public Page<EmergenciaDTO> buscarTodasEmergencias(Pageable pageable) {
        NativeSearchQuery nativeSearchQueryBuilder = new NativeSearchQueryBuilder()

            .withSourceFilter(new FetchSourceFilterBuilder().withIncludes
                (includes).build())
            .withPageable(pageable)
            .build();

        return emergenciaSearchRepository.search(nativeSearchQueryBuilder)
            .map(emergenciaMapper::toDto);
    }

    private void filter(BoolQueryBuilder queryBuilder, String name, String valueName) {
        if (!Strings.isNullOrEmpty(valueName)) {
            queryBuilder.must(QueryBuilders.matchQuery(name,valueName));
        }
    }
}

