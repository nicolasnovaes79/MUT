package com.projeto.mut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Configuracao;
import com.projeto.mut.repository.ConfiguracaoRepository;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public Configuracao getConfiguracao() {
        // Obtém a configuração, mas não retorna o id
        Configuracao configuracao = configuracaoRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));

        // Remover o id
        configuracao.setId(null);

        return configuracao; // Retorna a configuração sem o id
    }

    public Configuracao salvarConfiguracao(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }
}
