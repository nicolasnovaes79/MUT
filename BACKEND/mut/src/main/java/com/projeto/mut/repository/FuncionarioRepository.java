package com.projeto.mut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.mut.Entity.Funcionario;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByEmpresaId(Long empresaId);
}

