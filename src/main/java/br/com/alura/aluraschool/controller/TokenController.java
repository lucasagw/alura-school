package br.com.alura.aluraschool.controller;


import br.com.alura.aluraschool.model.entity.Profile;
import br.com.alura.aluraschool.model.record.LoginRequest;
import br.com.alura.aluraschool.model.record.LoginResponse;
import br.com.alura.aluraschool.repository.UserSchoolRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UserSchoolRepository userSchoolRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserSchoolRepository userSchoolRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userSchoolRepository = userSchoolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        var user = userSchoolRepository.findByUserKeyUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        var now = Instant.now();
        var expiresIn = 600L; //10min

        var scopes = user.get().getProfiles()
                .stream()
                .map(Profile::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("alura-api")
                .subject(user.get().getUserKey().getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("email", user.get().getUserKey().getEmail())
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

}



