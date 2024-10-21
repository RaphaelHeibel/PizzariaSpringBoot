package com.cursomateus.pizzariadankicode.demo.user;

import com.cursomateus.pizzariadankicode.demo.Config.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager _authenticator;
    private final TokenService _tokenService;

    @PostMapping
    public ResponseEntity credentialsValidation(@RequestBody @Valid UserCredentialsDTO credentials) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
        Authentication authentication = _authenticator.authenticate(token);

        return ResponseEntity.ok(_tokenService.createToken((Usuario) authentication.getPrincipal()));
    }
}
