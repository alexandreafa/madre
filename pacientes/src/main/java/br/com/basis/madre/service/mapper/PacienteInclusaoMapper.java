package br.com.basis.madre.service.mapper;


import br.com.basis.madre.domain.Paciente;
import br.com.basis.madre.service.dto.PacienteDTO;
import br.com.basis.madre.service.dto.PacienteInclusaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Paciente} and its DTO {@link PacienteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CartaoSUSMapper.class, ResponsavelMapper.class, DocumentoMapper.class, CertidaoMapper.class, OcupacaoMapper.class, ReligiaoMapper.class, MunicipioMapper.class, EtniaMapper.class, GenitoresMapper.class, NacionalidadeMapper.class, RacaMapper.class, EstadoCivilMapper.class,TelefoneMapper.class, EnderecoMapper.class})
public interface PacienteInclusaoMapper extends EntityMapper<PacienteInclusaoDTO, Paciente> {


    @Mapping(source = "ocupacao.id", target = "ocupacaoId")
    @Mapping(source = "religiao.id", target = "religiaoId")
    @Mapping(source = "naturalidade.id", target = "naturalidadeId")
    @Mapping(source = "etnia.id", target = "etniaId")
    @Mapping(source = "genitores.id", target = "genitoresId")
    @Mapping(source = "nacionalidade.id", target = "nacionalidadeId")
    @Mapping(source = "raca.id", target = "racaId")
    @Mapping(source = "estadoCivil.id", target = "estadoCivilId")
    PacienteInclusaoDTO toDto(Paciente paciente);


    //@Mapping(source = "certidaoId", target = "certidao")
    @Mapping(source = "ocupacaoId", target = "ocupacao")
    @Mapping(source = "religiaoId", target = "religiao")
    @Mapping(source = "naturalidadeId", target = "naturalidade")
    @Mapping(source = "etniaId", target = "etnia")
    @Mapping(source = "genitoresId", target = "genitores")
    @Mapping(source = "nacionalidadeId", target = "nacionalidade")
    @Mapping(source = "racaId", target = "raca")
    @Mapping(source = "estadoCivilId", target = "estadoCivil")
    Paciente toEntity(PacienteInclusaoDTO pacienteDTO);

    default Paciente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setId(id);
        return paciente;
    }
}
