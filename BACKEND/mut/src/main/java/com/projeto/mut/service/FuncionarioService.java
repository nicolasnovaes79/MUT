package com.projeto.mut.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Funcionario;
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

    // Método para listar funcionários despachados (despachado = true)


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
}
