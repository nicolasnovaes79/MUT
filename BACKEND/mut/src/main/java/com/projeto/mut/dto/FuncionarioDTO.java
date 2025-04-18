package com.projeto.mut.dto;

import java.time.LocalDate;

//DTO para expor os dados do Funcionario, incluindo uma versão simplificada da Empresa
public record FuncionarioDTO(
 Long id,
 String nome,
 String email,
 String cargo,
 Double salario,
 LocalDate dataAdmissao,
 boolean despachado,
 EmpresaDTO empresa
) {
	
}