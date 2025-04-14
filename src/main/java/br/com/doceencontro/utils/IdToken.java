package br.com.doceencontro.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.doceencontro.model.Usuario;

public class IdToken {
	public static String get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Usuario usuario = (Usuario) authentication.getPrincipal();
	    
	    return usuario.getId();
	}
}
