package com.example.festora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagens")
public class Imagem extends Arquivo {

	public Imagem() {

	}

	public Imagem(String id, String nome, String caminho) {
		super(id, nome, caminho);
	}
	
	

}
