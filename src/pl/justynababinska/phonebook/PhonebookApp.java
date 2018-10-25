package pl.justynababinska.phonebook;
import java.io.IOException;

public class PhonebookApp {
	public static void main(String[] args) throws IOException {
		PhonebookController controller = new PhonebookController();
		controller.loop();
	}
}
