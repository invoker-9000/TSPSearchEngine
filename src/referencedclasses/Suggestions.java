package referencedclasses;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Suggestions {
	
	public static TST<Integer> tst = new TST<Integer>();
	public static void buildTST(File file) throws Exception{
		String str = new String(Files.readAllBytes(Paths.get(file.getPath()))); // read the file
		//  extract only words
		Pattern pat = Pattern.compile("[\\w]+"); // extract each and every word from the file
		Matcher  m = pat.matcher(str); // match it with the string
		ArrayList<String> words= new ArrayList<String>(); 
		while(m.find()) {
				words.add(m.group());// save all the words into array list
			}
		for (int i = 0; i < words.size(); i++) {
			if(tst.contains(words.get(i))) {
				tst.put(words.get(i), tst.get(words.get(i))+1); // if we get the words in the tst then increment the count by 1
			}
			else
			tst.put(words.get(i),1); // if we dont get the word in the TST then add the value as 1
		}	
	}
	
		public static Queue<String> getSuggestion(String keyword){
		Queue<String> suggestions = (Queue<String>) tst.prefixMatch(keyword); // returns the queue of all the words starting with the keyword with the help of TST
		return suggestions;
	}
	
}
