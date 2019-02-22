package urlParser;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Parser {
	private String link;
	private HashMap<String, Integer> word_holder;
	private HashMap<String, Integer> sorted_word_holder;
	
	public Parser(String link) {
		this.link = link;
		word_holder = new HashMap<String, Integer>();
		sorted_word_holder = new HashMap<String, Integer>();
	}
	
	/* create() assembles a document from the url specified and a String is created
	 * from the text. The String is then converted to lower case for uniformity's sake.
	 * The individual words in the String are parsed, omitting spaces and other "non-word"
	 * characters, into an Array. This array is then traversed and each unique word is placed
	 * as a key into a HashMap with its value being incremented every time that word is found. 
	 */
	public void create() throws IOException {
		String words = Jsoup.connect(link).get().text().toLowerCase();
		String[] word_array = words.split("[^a-z\']+");
		for (int i = 0; i < word_array.length; i++) {
			if (!word_holder.containsKey(word_array[i])) {
				word_holder.put(word_array[i], 1);
			} else {
				int temp_value = word_holder.get(word_array[i]);
				temp_value++;
				word_holder.put(word_array[i], temp_value);
			}
		}
	}
	// sort_by_frequency() arranges the HashMap 'word_holder' in descending order of the mapped values 
	public void sort_by_frequency() {
		sorted_word_holder = word_holder.entrySet()
        .stream()
        .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	// print_most_frequent() prints the first 25 key pairs in the HashMap. 
	public void print_most_frequent() {
		int counter = 1;
		String output = "";
		// if less than 25 entries, iterator.hasNext() should break the loop.
		for(Iterator<Map.Entry<String,Integer>> iterator 
				 = sorted_word_holder.entrySet().iterator(); iterator.hasNext();) {
			if (counter < 26) {
				Entry<String, Integer> next_entry = iterator.next();
				output += counter + ".   " + next_entry.getKey() + " -> " + next_entry.getValue() + "\n";
				counter++;
			} else {
				break;
			}
		}
		if (counter < 26) {
			output += "(There are less than 25 words on the specified page.)";
		}
		// creates UI that displays the output 
		JOptionPane.showMessageDialog( 
				null, "The 25 most frequent words on this page are:" + "\n" + output, 
				"Results", JOptionPane.PLAIN_MESSAGE); 
				System.exit(0);
		
		

	}
}
