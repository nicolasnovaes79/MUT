package com.projeto.mut.dto;

import java.time.OffsetDateTime;

public record TokenEmpresaDTO(
    String token,
    OffsetDateTime dataExpiracao,
    boolean utilizado
) {}
