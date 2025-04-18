package com.projeto.mut.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.mut.dto.TokenEmpresaDTO;
import com.projeto.mut.Entity.TokenEmpresa;
import com.projeto.mut.repository.TokenEmpresaRepository;
import com.projeto.mut.service.TokenEmpresaService;

@RestController
@RequestMapping("/api/token-empresa")
public class TokenEmpresaController {

    @Autowired
    private TokenEmpresaService tokenEmpresaService;

    @Autowired
    private TokenEmpresaRepository tokenEmpresaRepository;

    // Endpoint para gerar um novo token para uma empresa
    @PostMapping("/gerar/{empresaId}")
    public ResponseEntity<TokenEmpresaDTO> gerarToken(@PathVariable Long empresaId) {
        TokenEmpresaDTO tokenGerado = tokenEmpresaService.gerarTokenParaEmpresa(empresaId);
        return ResponseEntity.ok(tokenGerado);
    }

    @GetMapping("/validar/{token}")
    public ResponseEntity<Boolean> validarToken(@PathVariable String token) {
        boolean valido = tokenEmpresaService.validarToken(token);
        return ResponseEntity.ok(valido);
    }

    @GetMapping("/{token}")
    public ResponseEntity<TokenEmpresa> buscarPorToken(@PathVariable String token) {
        Optional<TokenEmpresa> tokenOptional = tokenEmpresaRepository.findByToken(token);
        return tokenOptional.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/consultar/{empresaId}")
    public ResponseEntity<TokenEmpresaDTO> buscarTokenValidoPorEmpresa(@PathVariable Long empresaId) {
        TokenEmpresaDTO tokenDTO = tokenEmpresaService.buscarTokenValidoPorEmpresa(empresaId);

        if (tokenDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tokenDTO);
    }

}
