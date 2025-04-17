package br.com.doceencontro.model;

public enum Tipo {
	BEBE("Chá de Bebê"),
	FRALDAS("Chá de Fraldas"),
	REVELACAO("Chá Revelação");
	
	
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
