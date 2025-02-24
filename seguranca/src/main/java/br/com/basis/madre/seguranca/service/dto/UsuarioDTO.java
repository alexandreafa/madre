package br.com.basis.madre.seguranca.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link br.com.basis.madre.seguranca.domain.Usuario} entity.
 */
public class UsuarioDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer codigo;

    private String login;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuarioDTO)) {
            return false;
        }

        return id != null && id.equals(((UsuarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsuarioDTO{" +
            "id=" + getId() +
            ", codigo=" + getCodigo() +
            ", login='" + getLogin() + "'" +
            "}";
    }
}
