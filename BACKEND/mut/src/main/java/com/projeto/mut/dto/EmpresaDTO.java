package com.projeto.mut.dto;

import java.time.LocalDateTime;

//DTO para expor os dados da Empresa sem as authorities
public record EmpresaDTO(
 Long id,
 String nome,
 String cnpj,
 String endereco,
 String telefone,
 String email,
 String login,
 LocalDateTime dataCriacao
) {}