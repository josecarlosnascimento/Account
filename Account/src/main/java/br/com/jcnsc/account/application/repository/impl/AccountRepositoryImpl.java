package br.com.jcnsc.account.application.repository.impl;


import br.com.jcnsc.account.domain.Account;
import br.com.jcnsc.account.domain.Creditor;
import br.com.jcnsc.account.domain.Situation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> findByExpireDateAndDescription(LocalDate startDate,
                                                        LocalDate endDate,
                                                        String description) {

        String sql = """
				SELECT ct.id, ct.data_vencimento, ct.data_pagamento, ct.valor, ct.descricao, ct.situacao,
				ct.credor_id, cr.nome, cr.cpf_cnpj
				FROM contas ct
							LEFT JOIN credor cr ON cr.id = ct.credor_id
                        WHERE data_vencimento BETWEEN ? AND ?
					""";

        if(StringUtils.hasText(description)){
            sql += " AND ct.descricao LIKE ?";
        }

        List<Object> filters = new ArrayList<>();
        filters.add(startDate);
        filters.add(endDate);

        if(StringUtils.hasText(description)){
            filters.add("%"+description+"%");
        }

        System.out.println(sql);

        Object[] args = filters.toArray();

        return jdbcTemplate.query(sql, new RowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setExpireDate(LocalDate.parse(rs.getDate("data_vencimento").toString()));
                account.setPaymentDate(LocalDate.parse(rs.getDate("data_pagamento").toString()));
                account.setValue(rs.getBigDecimal("valor"));
                account.setSituation(Situation.valueOf(rs.getString("situacao")));
                account.setDescription(rs.getString("descricao"));

                if(rs.getLong("credor_id") > 0){
                    Creditor creditor = new Creditor();
                    creditor.setId(rs.getLong("credor_id"));
                    creditor.setName(rs.getString("nome"));
                    creditor.setCpfCnpj(rs.getString("cpf_cnpj"));
                    account.setCreditor(creditor);
                }
                return account;
            }
        }, args);
    }

}
