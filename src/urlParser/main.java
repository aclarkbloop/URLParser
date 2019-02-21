package urlParser;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		String url = JOptionPane.showInputDialog("Please enter a valid url:","");
		// creates a new Parser object with the URL inputed by the user
		Parser link_parser = new Parser(url);
		try {
			link_parser.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		link_parser.sort_by_frequency();
		link_parser.print_most_frequent();
	}
}
