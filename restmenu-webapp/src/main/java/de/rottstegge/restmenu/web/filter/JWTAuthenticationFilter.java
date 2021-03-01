package de.rottstegge.restmenu.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rottstegge.restmenu.model.JwtResponse;
import de.rottstegge.restmenu.model.User;
import de.rottstegge.restmenu.service.JwtService;
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

import static de.rottstegge.restmenu.web.filter.JWTConstants.CONTENT_TYPE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService, String signinUrl) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

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

        JwtResponse jwtResponse = jwtService.create(user.getUsername());

        String body = new ObjectMapper().writeValueAsString(jwtResponse);

        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}
