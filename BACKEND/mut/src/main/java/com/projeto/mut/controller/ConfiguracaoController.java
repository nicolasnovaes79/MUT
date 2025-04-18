package com.projeto.mut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Configuracao;
import com.projeto.mut.service.ConfiguracaoService;

@RestController
@RequestMapping("/api/configuracoes")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<Configuracao> getConfiguracao() {
        try {
            Configuracao configuracao = configuracaoService.getConfiguracao();
            return ResponseEntity.ok(configuracao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Configuracao> salvarConfiguracao(@RequestBody Configuracao configuracao) {
        try {
            Configuracao configuracaoSalva = configuracaoService.salvarConfiguracao(configuracao);
            return ResponseEntity.status(HttpStatus.CREATED).body(configuracaoSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null);
        }
    }
}
