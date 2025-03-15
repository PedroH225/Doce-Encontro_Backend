package com.example.festora.model;

public enum Tipo {
	OUTRO("Outro"),
	CHURRASCO("Churrasco"),
	ANIVERSARIO("Aniversário"),
	CASAMENTO("Casamento"),
	FORMATURA("Formatura"),
	CHADEBEBE("Chá de Bebê"),
	HALLOWEEN("Halloween"),
	REVEILLON("Reveillon"),
	CONFRATERNIZACAO("Confraternização"),
	CARNAVAL("Carnaval"),
	JUNINA("Festa Junina"),
	INAUGURACAO("Inauguração");
	
	private String tipo;
	
	Tipo(String tipo) {
		this.tipo = tipo;
	}
	
	public static Tipo fromString(String text) {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.tipo.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrado para a string fornecida: " + text);
    }
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
