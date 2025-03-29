package com.example.festora.model.dtos;

import org.hibernate.validator.constraints.Length;

import com.example.festora.annotation.SenhaForte;
import com.example.festora.utils.MensagemErro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
		@NotBlank(message = MensagemErro.OBRIGATORIO) @Length(min = 5, message = MensagemErro.MIN) 
		@Length(max = 20, message = MensagemErro.MAX)
		String nome, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO) @Email(message = MensagemErro.INVALIDO) 
		String email, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO) @SenhaForte 
		@Length(max = 20, message = MensagemErro.MAX)
		String senha
		
		) {
    
}
