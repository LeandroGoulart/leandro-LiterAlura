package com.goulart.leandro_LiterAlura.dto;

public record LivroDTO(
        Long id,

        String titulo,

        String autor,

        String descricao,

        String urlCapa
) {
}
