package pl.justynababinska.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileController {
	private static final String FILE_NAME = "phonebook.csv";

	public static void save(Phonebook phoneBook) throws IOException {
		FileWriter fileWriter = new FileWriter(FILE_NAME);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		for (Contact contact : phoneBook) {
			bufferedWriter.write(contact.toCSV());
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
		System.out.println("Ksi¹¿ka zapisana.");

	}

	public static Phonebook read() throws IOException {
		Phonebook phonebook = null;

		FileReader fileReader = new FileReader(FILE_NAME);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		try (fileReader; bufferedReader;) {
			Map<String, Contact> contacts = bufferedReader.lines().map(line -> line.split(";"))
					.map(split -> new Contact(split[0], split[1]))
					.collect(Collectors.toMap(Contact::getName, Function.identity()));
			phonebook = new Phonebook(new TreeMap<>(contacts));

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return phonebook != null ? phonebook : new Phonebook();
	}

}