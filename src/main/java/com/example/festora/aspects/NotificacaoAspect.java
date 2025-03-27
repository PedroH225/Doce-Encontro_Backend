package com.example.festora.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.festora.service.NotificacaoService;

@Aspect
@Component
public class NotificacaoAspect {

	private NotificacaoService notificacaoServie;

	public NotificacaoAspect(NotificacaoService notificacaoServie) {
		this.notificacaoServie = notificacaoServie;
	}
	
	
	
	
}



