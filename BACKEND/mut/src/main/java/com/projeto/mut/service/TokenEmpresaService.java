package com.projeto.mut.service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.Entity.TokenEmpresa;
import com.projeto.mut.dto.TokenEmpresaDTO;
import com.projeto.mut.repository.EmpresaRepository;
import com.projeto.mut.repository.TokenEmpresaRepository;

@Service
public class TokenEmpresaService {

    @Autowired
    private TokenEmpresaRepository tokenEmpresaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // Gerar um novo token para uma empresa
    public TokenEmpresaDTO gerarTokenParaEmpresa(Long empresaId) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(empresaId);

        if (empresaOptional.isEmpty()) {
            throw new RuntimeException("Empresa não encontrada com ID: " + empresaId);
        }

        Empresa empresa = empresaOptional.get();

        // Criar novo token
        TokenEmpresa tokenEmpresa = new TokenEmpresa();
        tokenEmpresa.setEmpresa(empresa);
        tokenEmpresa.setToken(UUID.randomUUID().toString());
        tokenEmpresa.setDataCriacao(OffsetDateTime.now());
        tokenEmpresa.setDataExpiracao(OffsetDateTime.now().plusDays(7));
        tokenEmpresa.setUtilizado(false);

        TokenEmpresa salvo = tokenEmpresaRepository.save(tokenEmpresa);

        // Retornar DTO
        return new TokenEmpresaDTO(
            salvo.getToken(),
            salvo.getDataExpiracao(),
            salvo.getUtilizado()
        );
    }

    // Validar se um token ainda está válido
    public boolean validarToken(String token) {
        Optional<TokenEmpresa> tokenOptional = tokenEmpresaRepository.findByToken(token);

        if (tokenOptional.isEmpty()) return false;

        TokenEmpresa tokenEmpresa = tokenOptional.get();

        boolean naoExpirado = tokenEmpresa.getDataExpiracao().isAfter(OffsetDateTime.now());
        boolean naoUtilizado = !tokenEmpresa.getUtilizado();

        return naoExpirado && naoUtilizado;
    }

    // Marcar token como utilizado
    public void marcarTokenComoUtilizado(String token) {
        Optional<TokenEmpresa> tokenOptional = tokenEmpresaRepository.findByToken(token);

        tokenOptional.ifPresent(tokenEmpresa -> {
            tokenEmpresa.setUtilizado(true);
            tokenEmpresaRepository.save(tokenEmpresa);
        });
    }
}
