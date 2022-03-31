package tspsearch;

import java.util.Scanner;
import referencedclasses.Queue;
import referencedclasses.SpellChecker;
import referencedclasses.Suggestions;

public class SearchEngine {
	public static void main(String[] args) throws Exception{
		
		PreProcessor.preProcessor();
		
		
		String choice = "";
		Scanner in = new Scanner(System.in);
		String key;
		
		while(true) {
			System.out.println("Please select the operations you want to perform from the below menu");
			System.out.println("Type 1 To Search \nType 2 To get Suggestion to complete the word \nType 3 To check the spelling \nType 4 Exit\n");
			choice = in.next();
			switch (choice) {
			case "1":
				System.out.println("Search with our TSP Search Engine:");
				key = in.next();
				Ranking.search(key.toLowerCase(), 5);
				break;
		
			case "2":
				System.out.println("Enter a word to get the autocomplete suggestions :");
				key = in.next();
				Queue<String> suggestions = Suggestions.getSuggestion(key);
				for (int i = 0; i < suggestions.size(); i++) {
					System.out.println((suggestions.dequeue()));
				}
				break;
			
			case "3":
				System.out.println("Enter a word to check its spelling:");
				key = in.next();
				SpellChecker.spellCheck(key);
				break;
			
			case "4":
				System.out.println("Exiting");
				System.out.println("Bye, have a great day, see you again soon!");
				in.close();
				System.exit(0);
				break;
			
			default:
				System.out.println("Invalid choice, Please try again!!");
				break;
			}
			
		}
		
		
	}
	
}
