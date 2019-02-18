package urlParser;
import java.util.HashMap;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// creates a new Parser object with the URL inputed by the user
		Parser link_parser = new Parser(scan.next());
		try {
			link_parser.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		link_parser.sort_by_frequency();
		link_parser.print_most_frequent();
	}
}
