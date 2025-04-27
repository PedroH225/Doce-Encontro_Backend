package br.com.doceencontro.model.dtos;

import org.hibernate.validator.constraints.Length;

import br.com.doceencontro.annotation.SenhaForte;
import br.com.doceencontro.utils.MensagemErro;
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
		String senha,
		
		String senhaRepitida
		
		) {
    
}
