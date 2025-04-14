package br.com.doceencontro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagens")
@PrimaryKeyJoinColumn(name = "id")
public class Imagem extends Arquivo {
	
	public Imagem() {

	}

	public Imagem(String id, String nome, String caminho, String tipo, Evento evento) {
		super(id, nome, caminho, tipo, evento);
	}

	

	
	
	

}
