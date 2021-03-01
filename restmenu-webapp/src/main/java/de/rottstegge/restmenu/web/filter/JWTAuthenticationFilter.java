package de.rottstegge.restmenu.web.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.rottstegge.restmenu.model.JwtResponse;
import de.rottstegge.restmenu.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static de.rottstegge.restmenu.web.filter.JWTConstants.CONTENT_TYPE;
import static de.rottstegge.restmenu.web.filter.JWTConstants.TOKEN_TYPE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final String secretKey;
    private final long expirationMs;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey, long expirationMs, String signinUrl) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;

        setFilterProcessesUrl(signinUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                    credentials.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .sign(Algorithm.HMAC512(secretKey));

        JwtResponse jwtResponse = new JwtResponse(token, TOKEN_TYPE.toLowerCase(), expirationMs / 1000);

        String body = new ObjectMapper().writeValueAsString(jwtResponse);

        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}
