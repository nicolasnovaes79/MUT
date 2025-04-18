package com.projeto.mut.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.Entity.Funcionario;
import com.projeto.mut.dto.EmpresaDTO;
import com.projeto.mut.dto.FuncionarioDTO;
import com.projeto.mut.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }
    
    public Optional<Funcionario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    
    public Funcionario atualizar(Funcionario funcionario) {
        if (!repository.existsById(funcionario.getId())) {
            throw new RuntimeException("Funcionário não encontrado com o ID: " + funcionario.getId());
        }
        return repository.save(funcionario);
    }
    
 // Método para listar funcionários por ID da empresa
    public List<Funcionario> listarPorEmpresa(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }
    
    public List<Funcionario> listarDespachadosPorEmpresa(Long empresaId) {
        return repository.findByEmpresaIdAndDespachadoTrue(empresaId);
    }


    
    // Método para converter uma entidade Funcionario para FuncionarioDTO
    public FuncionarioDTO converterParaDTO(Funcionario funcionario) {
        Empresa empresa = funcionario.getEmpresa();
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
        return new FuncionarioDTO(
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getEmail(),
            funcionario.getCargo(),
            funcionario.getSalario(),
            funcionario.getDataAdmissao(),
            funcionario.isDespachado(),
            empresaDTO
        );
    }
    
    // Método auxiliar para converter uma lista de entidades para DTOs
    public List<FuncionarioDTO> converterListaParaDTO(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
}
