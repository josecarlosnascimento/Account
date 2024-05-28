package br.com.jcnsc.account.infra.security;

import br.com.jcnsc.account.domain.auth.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secret}")
    private String secret;

    public String tokenGenerate(Users users){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("account-api")
                    .withSubject(users.getUsername())
                    .withExpiresAt(getExpireDate())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token da api", e);
        }
    }

    public String validateToken(String token) {

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("account-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException("Erro ao validar token da api", e);
        }
    }

        private Instant getExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
