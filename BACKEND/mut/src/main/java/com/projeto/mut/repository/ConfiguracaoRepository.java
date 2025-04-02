package com.projeto.mut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.mut.Entity.Configuracao;



@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
    // Podemos adicionar métodos customizados aqui, se necessário
}

