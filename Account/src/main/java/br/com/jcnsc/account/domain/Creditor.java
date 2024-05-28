package br.com.jcnsc.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "credor")
public class Creditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @OneToMany(mappedBy = "creditor")
    private Set<Account> accounts;
}
