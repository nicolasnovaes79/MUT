package com.projeto.mut.dto;

public record ConfiguracaoDTO(
		 Long id,
		 boolean nome,
		 boolean cpf,
		 boolean email,
		 boolean telefone,
		 boolean cargo,
		 boolean salario,
		 boolean dataAdmissao,
		 boolean ativo) {

}
