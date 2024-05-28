package br.com.jcnsc.account.application.repository.auth;

import br.com.jcnsc.account.domain.auth.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<UserDetails> findByUsername(String username);

}
