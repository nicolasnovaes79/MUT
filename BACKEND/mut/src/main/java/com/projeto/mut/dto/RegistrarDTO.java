package com.projeto.mut.dto;

import com.projeto.mut.enums.EmpresaRole;

public record RegistrarDTO(
    String login,
    String senha,
    String nome,
    String cnpj,
    String endereco,
    String telefone,
    String email,
    EmpresaRole role
) { }
