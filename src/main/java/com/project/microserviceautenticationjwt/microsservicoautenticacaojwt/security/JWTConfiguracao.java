package com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.security;

import com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.service.DetalheUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    //Indicando para o Spring Security usar as minhas classes como classe base de implementação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    //desabilitei o csrf para desenvolvimento apenas,
    // mas ele resolve ataques na aplicação então é importante utilizar no projeto real
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll() //login permite tudo
                .anyRequest().authenticated() //qualquer outra chamada precisa de autenticação
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //Cors permite que a aplicação possa receber requisição de outros domínios
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
