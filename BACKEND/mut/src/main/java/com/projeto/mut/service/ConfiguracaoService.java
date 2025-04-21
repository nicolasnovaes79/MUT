package com.projeto.mut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Configuracao;
import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.Entity.Funcionario;
import com.projeto.mut.dto.ConfiguracaoDTO;
import com.projeto.mut.dto.EmpresaDTO;
import com.projeto.mut.dto.FuncionarioDTO;
import com.projeto.mut.repository.ConfiguracaoRepository;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public Configuracao getConfiguracaoPorEmpresa(Long empresaId) {
        return configuracaoRepository.findByEmpresaId(empresaId)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada para esta empresa"));
    }

    public Configuracao salvarConfiguracao(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }
    
    // Método para converter uma entidade Configuracao para ConfiguracaoDTO
    public ConfiguracaoDTO converterParaDTO(Configuracao configuracao) {
        Empresa empresa = configuracao.getEmpresa();
        EmpresaDTO empresaDTO = null;
        if (empresa != null) {
            empresaDTO = new EmpresaDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj(),
                empresa.getEndereco(),
                empresa.getTelefone(),
                empresa.getEmail(),
                empresa.getLogin(),
                empresa.getDataCriacao()
            );
        }
        return new ConfiguracaoDTO(
            configuracao.getId(),
        	configuracao.isNome(),
        	configuracao.isCpf(),
        	configuracao.isEmail(),
        	configuracao.isTelefone(),
        	configuracao.isCargo(),
        	configuracao.isSalario(),
        	configuracao.isDataAdmissao(),
        	configuracao.isAtivo()
        );
    }
}
