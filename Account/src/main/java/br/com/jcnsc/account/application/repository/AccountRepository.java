package br.com.jcnsc.account.application.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jcnsc.account.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account a set a.situation = 'PAGA', a.paymentDate = CURRENT_DATE WHERE a.id = ?1")
    void updateToPaga(Long id);

    @Modifying
    @Query("UPDATE Account a set a.situation = 'PENDENTE', a.paymentDate = NULL WHERE a.id = ?1")
    void updateToPendente(Long id);

    @Query("SELECT COALESCE(SUM(a.value),0) FROM Account a WHERE a.paymentDate BETWEEN ?1 AND ?2")
    BigDecimal sumValuePaid(LocalDate dtIni, LocalDate dtFim);

}
