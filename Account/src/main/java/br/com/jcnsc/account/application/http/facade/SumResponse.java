package br.com.jcnsc.account.application.http.facade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SumResponse(

        @JsonProperty("inicio-periodo")
        LocalDate inicioPeriodo,

        @JsonProperty("fim-periodo")
        LocalDate fimPeriodo,

        @JsonProperty("valor")
        BigDecimal value

){}
