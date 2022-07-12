package com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.data;

import com.project.microserviceautenticationjwt.microsservicoautenticacaojwt.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//Implementar a interface UserDetails do Spring Security
public class DetalheUsuarioData implements UserDetails {

    private final Optional<UsuarioModel> usuario;

    public DetalheUsuarioData(Optional<UsuarioModel> usuario) {
    this.usuario = usuario;
    }

    //Permissões do Usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    //Senha
    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioModel()).getPassword();
    }

    //Usuário
    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getLogin();
    }

    //Verificar se a conta não está expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Verificar se a conta não está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Verificar se a credencial não está expirada
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Verificar se conta está ativa
    @Override
    public boolean isEnabled() {
        return true;
    }
}
