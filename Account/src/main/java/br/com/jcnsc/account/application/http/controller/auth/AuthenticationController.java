package br.com.jcnsc.account.application.http.controller.auth;

import br.com.jcnsc.account.application.http.exception.UserException;
import br.com.jcnsc.account.application.http.facade.auth.Authentication;
import br.com.jcnsc.account.application.http.facade.auth.LoginResponse;
import br.com.jcnsc.account.application.http.facade.auth.Register;
import br.com.jcnsc.account.application.repository.auth.UserRepository;
import br.com.jcnsc.account.domain.auth.Users;
import br.com.jcnsc.account.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Authentication authentication){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authentication.username(), authentication.password());
        var auth  = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.tokenGenerate((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Register register){
        var user =  userRepository.findByUsername(register.username());

        if(user.isPresent()){
            throw new UserException(String.format("Já existe um usuário com o username %s", register.username()));
        }

        String encryptedPass = new BCryptPasswordEncoder().encode(register.password());
        Users users = new Users(register.username(), encryptedPass, register.role());
        var userSave = this.userRepository.save(users);
        return ResponseEntity.ok().build();
    }
}
