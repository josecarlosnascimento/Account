package br.com.jcnsc.account.application.http.facade;

import br.com.jcnsc.account.domain.Situation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountResponse (
        @JsonProperty("id")
        Long id,

        @JsonProperty("data_vencimento")
        LocalDate expireDate,

        @JsonProperty("data_pagamento")
        LocalDate paymentDate,

        @JsonProperty("valor")
        BigDecimal value,

        @JsonProperty("descricao")
        String description,

        @JsonProperty("situacao")
        @Enumerated(EnumType.STRING)
        Situation situation,

        @JsonProperty("credor")
        CreditorResponse creditor){}
