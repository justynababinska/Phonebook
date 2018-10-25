package pl.justynababinska.phonebook;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PhonebookController {
	private Phonebook phonebook;
	private Scanner sc = new Scanner(System.in);

	public PhonebookController() throws IOException {
		phonebook = FileController.read();
	}

	public void loop() {
		Option option = null;
		do {
			printOption();
			try {
				option = chooseOption();
				runOption(option);
			} catch (NoSuchElementException ex) {
				System.out.println("Poda�e� nieprawid�owy numer.");
			}
		} while (option != Option.EXIT);
	}

	public void printOption() {
		System.out.println("Wybierz jedn� z dost�pnych opcji podaj�c jej numer:");
		Arrays.asList(Option.values()).forEach(System.out::println);
	}

	public Option chooseOption() {
		int number = sc.nextInt();
		sc.nextLine();
		return Option.convertIntToOption(number);
	}

	public void runOption(Option option) {

		switch (option) {
		case ADD:
			addContact();
			break;
		case SEARCH_BY_NAME:
			searchByName();
			break;
		case SEARCH_BY_PHONE:
			searchByPhoneNumber();
			break;
		case REMOVE:
			removeCoctact();
			break;
		case EXIT:
			close();
			break;
		}
	}

	private void addContact() {
		System.out.println("Podaj nazw� kontaktu:");
		String addName = sc.nextLine();
		System.out.println("Podaj numer telefonu:");
		String addPhoneNum = sc.nextLine();
		try {
			boolean add = phonebook.addNewCoctact(addName, addPhoneNum);
			if (add) {
				System.out.println("Kontakt dodany.");
			}
		} catch (IllegalArgumentException ex) {
			System.err.println("Nazwa i numer telefonu nie mog� by� puste.");
		}
	}

	private void searchByName() {
		System.out.println("Podaj fragment nazwy:");
		String searchingName = sc.nextLine();
		List<Contact> findedContacts = phonebook.searchByName(searchingName);
		if (findedContacts.isEmpty()) {
			System.out.println("Brak wynik�w.");
		} else {
			System.out.println("Lista odnalezionych kontak�w:");
			findedContacts.forEach(System.out::println);
		}
	}

	private void searchByPhoneNumber() {
		System.out.println("Podaj fragment numeru telefonu:");
		String searchingPhoneNum = sc.nextLine();
		List<Contact> findedContacts = phonebook.searchByPhoneNumber(searchingPhoneNum);
		if (findedContacts.isEmpty()) {
			System.out.println("Brak wynik�w.");
		} else {
			System.out.println("Lista odnalezionych kontak�w:");
			findedContacts.forEach(System.out::println);
		}
	}

	private void removeCoctact() {
		System.out.println("Podaj kogo usun�� z ksi��ki telefonicznej:");
		String removedName = sc.nextLine();
		boolean removedContact = phonebook.removeCoctact(removedName);
		if (removedContact) {
			System.out.println("Kontakt usuni�ty.");
		} else {
			System.out.println("Nie ma takiego kontaktu.");
		}
	}

	private void close() {
		sc.close();
		try {
			FileController.save(phonebook);
		} catch (IOException ex) {
			System.out.println("Nie uda�o si� zapisa� pliku");
		}
		System.out.println("Bye bye.");
	}

}
