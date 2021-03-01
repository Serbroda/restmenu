package de.rottstegge.restmenu.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import de.rottstegge.restmenu.model.JwtResponse;

public interface JwtService {

    JwtResponse create(String username);

    default String createToken(String username) {
        return create(username).getAccessToken();
    }

    default DecodedJWT verify(JwtResponse jwtResponse) {
        return verify(jwtResponse.getAccessToken());
    }

    DecodedJWT verify(String jwt);
}
