package com.projeto.mut.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projeto.mut.dto.RegistrarDTO;
import com.projeto.mut.enums.EmpresaRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private EmpresaRole role;
    
    private String login;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    // Construtores
    public Empresa() {
    }

    public Empresa(String login, String senha, EmpresaRole role) {
        this.login = login;
        this.senha = senha;
        this.role = role;
    }
    
    public Empresa(RegistrarDTO data, String senhaCriptografada, EmpresaRole role) {
        this.login = data.login();
        this.senha = senhaCriptografada;
        this.nome = data.nome();
        this.cnpj = data.cnpj();
        this.endereco = data.endereco();
        this.telefone = data.telefone();
        this.email = data.email();
        this.role = role;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EmpresaRole getRole() {
        return role;
    }

    public void setRole(EmpresaRole role) {
        this.role = role;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // Implementação dos métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == EmpresaRole.CLIENTE) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Você pode customizar depois se quiser tratar conta expirada
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Você pode customizar depois se quiser tratar conta bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Você pode customizar depois se quiser tratar credenciais expiradas
    }

    @Override
    public boolean isEnabled() {
        return true; // Você pode customizar depois se quiser tratar usuário desabilitado
    }
}
