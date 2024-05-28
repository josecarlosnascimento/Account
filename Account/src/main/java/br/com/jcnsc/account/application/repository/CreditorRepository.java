package br.com.jcnsc.account.application.repository;

import br.com.jcnsc.account.domain.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long> {
}
