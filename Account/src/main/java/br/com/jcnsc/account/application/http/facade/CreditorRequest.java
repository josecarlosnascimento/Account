package br.com.jcnsc.account.application.http.facade;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



public record CreditorRequest(
    @JsonProperty("id")
    Long id,
    @JsonProperty("name")
    @NotBlank(message = "Informe o nome do credor.")
    String name,
    @JsonProperty("cpf-cnpj")
    @NotBlank(message = "Informe o CPF ou CNPJ do credor.")
    String cpfCnpj){}
