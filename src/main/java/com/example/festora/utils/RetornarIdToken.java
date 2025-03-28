package com.example.festora.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.festora.model.Usuario;

public class RetornarIdToken {
	public static String get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Usuario usuario = (Usuario) authentication.getPrincipal();
	    
	    return usuario.getId();
	}
}
