package br.com.basis.madre.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaDeLeitoDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataDoLancamento;

    private String justificativa;

    @NotNull
    private Long leitoId;

    private Long origemId;

    private Long tipoId;

}
