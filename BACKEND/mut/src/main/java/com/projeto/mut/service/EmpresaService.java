package com.projeto.mut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.mut.Entity.Empresa;
import com.projeto.mut.repository.EmpresaRepository;

@Service
public class EmpresaService implements UserDetailsService {

    @Autowired
    EmpresaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String empresaNome) throws UsernameNotFoundException {
        Empresa empresa = repository.findByLogin(empresaNome);
        if (empresa == null) {
            throw new UsernameNotFoundException("Empresa não encontrada com login: " + empresaNome);
        }
        return empresa; // Empresa implementa UserDetails
    }

    public Empresa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Empresa buscarPorLogin(String login) {
        return repository.findByLogin(login);
    }
}
