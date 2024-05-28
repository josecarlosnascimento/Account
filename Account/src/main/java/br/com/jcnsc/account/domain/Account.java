package br.com.jcnsc.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contas")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate expireDate;

    @Column(name = "data_pagamento")
    private LocalDate paymentDate;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "descricao")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situation situation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credor_id")
    private Creditor creditor;
}
