package com.projeto.mut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mut.Entity.Configuracao;
import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.dto.ConfiguracaoDTO;
import com.projeto.mut.service.ConfiguracaoService;

@RestController
@RequestMapping("/api/configuracoes")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<ConfiguracaoDTO> getConfiguracao() {
        try {
            Empresa empresa = (Empresa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            Configuracao configuracao = configuracaoService.getConfiguracaoPorEmpresa(empresa.getId());
            ConfiguracaoDTO dto = configuracaoService.converterParaDTO(configuracao);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
        	System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ConfiguracaoDTO> salvarConfiguracao(@RequestBody Configuracao configuracao) {
        try {
            Empresa empresa =  (Empresa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            configuracao.setEmpresa(empresa);
          
            Configuracao configuracaoSalva = configuracaoService.salvarConfiguracao(configuracao);
            ConfiguracaoDTO dto = configuracaoService.converterParaDTO(configuracaoSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null);
        }
    }
}
