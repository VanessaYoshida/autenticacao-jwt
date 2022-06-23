package com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.security.config.Elements.JWT;

public class JWTValidarFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer";

    public JWTValidarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);


        String atributo = request.getHeader(HEADER_ATRIBUTO);

        if (atributo == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
            chain.doFilter(request, response);
            return;
        }
//            UsernamePasswordAuthenticationToken =
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String usuario = com.auth0.jwt.JWT.require(Algorithm.HMAC512(JWTAutenticarFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

        if (usuario == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
    }

}
