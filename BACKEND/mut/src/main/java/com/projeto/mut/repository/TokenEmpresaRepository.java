package com.projeto.mut.repository;

import java.time.OffsetDateTime;
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

    // Buscar o token mais recente, válido e não utilizado por empresa
    Optional<TokenEmpresa> findTopByEmpresaIdAndUtilizadoFalseAndDataExpiracaoAfterOrderByDataCriacaoDesc(
        Long empresaId, OffsetDateTime dataAtual
    );
}
