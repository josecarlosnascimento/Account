package br.com.jcnsc.account.application.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.jcnsc.account.domain.auth.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<UserDetails> findByUsername(String username);

}
