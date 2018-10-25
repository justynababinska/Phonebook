package pl.justynababinska.phonebook;
import java.util.NoSuchElementException;

public enum Option {
	ADD(0, "Dodaj kontakt"), 
	SEARCH_BY_NAME(1, "Szukaj po nazwie"), 
	SEARCH_BY_PHONE(2, "Szukaj po telefonie"),
	REMOVE(3, "Usuñ"), 
	EXIT(4, "Koniec");

	private int number;
	private String description;

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}

	private Option(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public static Option convertIntToOption(int num) {
		if (num < 0 || num > values().length) {
			throw new NoSuchElementException();
		}
		return values()[num];
	}

	@Override
	public String toString() {
		return number + " - " + description;
	}
}