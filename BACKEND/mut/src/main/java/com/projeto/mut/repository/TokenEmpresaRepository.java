package com.projeto.mut.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.mut.Entity.TokenEmpresa;

@Repository
public interface TokenEmpresaRepository extends JpaRepository<TokenEmpresa, Long> {

    // Buscar um token específico
    Optional<TokenEmpresa> findByToken(String token);

    // Buscar um token válido (não expirado e não utilizado)
    Optional<TokenEmpresa> findByTokenAndUtilizadoFalse(String token);
}

