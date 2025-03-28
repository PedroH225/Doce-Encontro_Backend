package com.example.festora.model.dtos;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String nome, @NotNull String email, @NotNull String senha) {
    
}
