package br.com.alura.aluraschool.util;

import br.com.alura.aluraschool.model.record.UserKeyRequest;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

public class JwtTokenUtils {

    public static UserKeyRequest getUserKeyFromToken(JwtAuthenticationToken token) {

        Map<String, Object> tokenAttributes = token.getTokenAttributes();

        String username = (String) tokenAttributes.get("sub");
        String email = (String) tokenAttributes.get("email");

        return new UserKeyRequest(username, email);
    }
}
