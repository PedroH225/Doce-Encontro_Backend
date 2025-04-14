package br.com.doceencontro.model;

public enum StatusAmizade {
	PENDENTE("Pendente"),
	ACEITO("Aceito");
	
	private String status;
	
	StatusAmizade(String status) {
		this.status = status;
	}
	
	public static StatusAmizade fromString(String text) {
        for (StatusAmizade status : StatusAmizade.values()) {
            if (status.status.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontrado para a string fornecida: " + text);
    }
	
	@Override
	public String toString() {
		return this.status;
	}
}
