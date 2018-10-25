package pl.justynababinska.phonebook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Phonebook implements Iterable<Contact> {
	private Map<String, Contact> phonebook = new TreeMap<>();

	public Phonebook() {
	}

	public Phonebook(Map<String, Contact> phonebook) {
		this.phonebook = phonebook;
	}

	public boolean addNewCoctact(String name, String phoneNumber) {
		if (name == null || phoneNumber == null)
			throw new NullPointerException("nazwa oraz numer telefonu nie mog¹ byæ null");
		if (name.isEmpty() || phoneNumber.isEmpty())
			throw new IllegalArgumentException("nazwa oraz numer telefonu nie mog¹ byæ puste");
		if (!phonebook.containsKey(name)) {
			phonebook.put(name, new Contact(name, phoneNumber));
			return true;
		} else {
			return false;
		}
	}

	public boolean removeCoctact(String name) {
		return phonebook.remove(name) != null;
	}

	public List<Contact> searchByName(String nameFragment) {
		List<Contact> findedCoctacts = new ArrayList<>();
		for (Entry<String, Contact> pBook : phonebook.entrySet()) {
			if (pBook.getKey().contains(nameFragment)) {
				findedCoctacts.add(pBook.getValue());
			}
		}
		return findedCoctacts;
	}

	public List<Contact> searchByPhoneNumber(String phoneNumberFragment) {
		List<Contact> findedCoctacts = new ArrayList<>();
		for (Contact cont : phonebook.values()) {
			if (cont.getPhoneNumber().contains(phoneNumberFragment)) {
				findedCoctacts.add(cont);
			}
		}
		return findedCoctacts;
	}

	@Override
	public Iterator<Contact> iterator() {
		return phonebook.values().iterator();
	}
}
