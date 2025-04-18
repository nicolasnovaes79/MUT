package com.projeto.mut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.dto.EmpresaDTO;
import com.projeto.mut.repository.EmpresaRepository;

@Service
public class EmpresaService implements UserDetailsService {

    @Autowired
    private EmpresaRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String empresaNome) throws UsernameNotFoundException {
        Empresa empresa = (Empresa) repository.findByLogin(empresaNome);
        if (empresa == null) {
            throw new UsernameNotFoundException("Empresa não encontrada: " + empresaNome);
        }
        return empresa;
    }
    
    public Empresa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    // Método para converter uma entidade Empresa para EmpresaDTO
    public EmpresaDTO converterParaDTO(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        return new EmpresaDTO(
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
}
