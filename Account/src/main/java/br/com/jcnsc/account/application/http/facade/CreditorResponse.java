package br.com.jcnsc.account.application.http.facade;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreditorResponse (

    @JsonProperty("id")
    Long id,

    @JsonProperty("name")
    String name,

    @JsonProperty("cpf-cnpj")
    String cpfCnpj){}

