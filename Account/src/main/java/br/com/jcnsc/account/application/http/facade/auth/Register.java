package br.com.jcnsc.account.application.http.facade.auth;

import br.com.jcnsc.account.domain.auth.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Register(
        @NotBlank(message = "Informe o username.")
        String username,
        
        @NotBlank(message = "Informe a senha.")
        String password,

        @NotNull(message = "Informe as roles.")
        UserRole role) {
}
