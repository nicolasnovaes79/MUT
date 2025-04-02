package com.projeto.mut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.mut.Entity.Funcionario;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Método para encontrar todos os funcionários com despachado = true
    List<Funcionario> findByDespachadoTrue();
}
