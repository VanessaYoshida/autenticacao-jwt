package com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.service;

import com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.data.DetalheUsuarioData;
import com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.model.UsuarioModel;
import com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    //Injetar o repositório para fazer a consulta do usuario
    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    //Consulta do usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional< UsuarioModel> usuario = repository.findByLogin(username);
        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }
}
