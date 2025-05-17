package br.com.doceencontro.model;

public enum Icone {
    MAIL("mail"),
    EVENT("event"),
    MESSAGE("message"),
    NOTIFICATION("notifications"),
    WARNING("warning"),
    INFO("info"),
    ERROR("error"),
    SUCCESS("check_circle"),
    CALENDAR("calendar_today");

	private String icone;
	
	Icone(String icone) {
		this.icone = icone;
	}
	
	public static Icone fromString(String text) {
        for (Icone icone : Icone.values()) {
            if (icone.icone.equalsIgnoreCase(text)) {
                return icone;
            }
        }
        throw new IllegalArgumentException("Nenhum icone encontrado para a string fornecida: " + text);
    }
	
	@Override
	public String toString() {
		return this.icone;
	}
}
