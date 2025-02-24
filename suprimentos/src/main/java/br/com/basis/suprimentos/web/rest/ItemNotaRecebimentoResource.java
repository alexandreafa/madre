package br.com.basis.suprimentos.web.rest;

import br.com.basis.suprimentos.service.ItemNotaRecebimentoService;
import br.com.basis.suprimentos.service.dto.ItemNotaRecebimentoDTO;
import br.gov.nuvem.comum.microsservico.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemNotaRecebimentoResource {
    private static final String ENTITY_NAME = "madresuprimentosItemNotaRecebimento";
    private final ItemNotaRecebimentoService itemNotaRecebimentoService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PostMapping("/itens-nota-recebimento")
    public ResponseEntity<ItemNotaRecebimentoDTO> createItemNotaRecebimento(@Valid @RequestBody ItemNotaRecebimentoDTO itemNotaRecebimentoDTO) throws URISyntaxException {
        log.debug("REST request to save ItemNotaRecebimento : {}", itemNotaRecebimentoDTO);
        if (itemNotaRecebimentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemNotaRecebimento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemNotaRecebimentoDTO result = itemNotaRecebimentoService.save(itemNotaRecebimentoDTO);
        return ResponseEntity.created(new URI("/api/itens-nota-recebimento/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/itens-nota-recebimento/{id}")
    public ResponseEntity<ItemNotaRecebimentoDTO> updateItemNotaRecebimento(@Valid @RequestBody ItemNotaRecebimentoDTO itemNotaRecebimentoDTO) throws URISyntaxException {
        log.debug("REST request to update ItemNotaRecebimento : {}", itemNotaRecebimentoDTO);
        if (itemNotaRecebimentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemNotaRecebimentoDTO result = itemNotaRecebimentoService.save(itemNotaRecebimentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemNotaRecebimentoDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/itens-nota-recebimento")
    public ResponseEntity<List<ItemNotaRecebimentoDTO>> getAllItemNotaRecebimentos(Pageable pageable) {
        log.debug("REST request to get a page of ItemNotaRecebimentos");
        Page<ItemNotaRecebimentoDTO> page = itemNotaRecebimentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/itens-nota-recebimento/{id}")
    public ResponseEntity<ItemNotaRecebimentoDTO> getItemNotaRecebimento(@PathVariable Long id) {
        log.debug("REST request to get ItemNotaRecebimento : {}", id);
        Optional<ItemNotaRecebimentoDTO> itemNotaRecebimentoDTO = itemNotaRecebimentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemNotaRecebimentoDTO);
    }

    @DeleteMapping("/itens-nota-recebimento/{id}")
    public ResponseEntity<Void> deleteItemNotaRecebimento(@PathVariable Long id) {
        log.debug("REST request to delete ItemNotaRecebimento : {}", id);
        itemNotaRecebimentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/_search/itens-nota-recebimento")
    public ResponseEntity<List<ItemNotaRecebimentoDTO>> searchItemNotaRecebimentos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ItemNotaRecebimentos for query {}", query);
        Page<ItemNotaRecebimentoDTO> page = itemNotaRecebimentoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
