package referencedclasses;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InvertedIndexBuilder {
	// Hashmap inside hashmap, (Keyword, hashmap ( filename , occurrence ) 
	public static HashMap<String,HashMap<String, Integer>> index = 
			new HashMap<String, HashMap<String,Integer>>();
	public static void invertedIndex(File file) throws Exception {
		
		// read file
		String string = new String(Files.readAllBytes(Paths.get(file.getPath()))); // read the text file and store it into a string
		
				
		//tokenise text to extract only words using Java Regex pakage and their methods
		Pattern pattern = Pattern.compile("[\\w]+"); // pattern to extract only a single word
		Matcher  patternMatcher = pattern.matcher(string); // match the pattern with our string
		ArrayList<String> words= new ArrayList<String>();
		while(patternMatcher.find()) {
			words.add(patternMatcher.group());
		}
		
		//build index hashMAp ( word , Hashmap ( Filename , ocurence)
		for (String word : words) {
			//if index already contains word.
			if(index.containsKey(word)) {   // here we check if the external hash-map already contains the word then simply increase the occurence in that file by 1
				HashMap<String, Integer> hashMap = index.get(word); 
				//number of occurrences 
	           // get the value of the external hash map and update the value of the internal hash map (occurrence of the word)
				if(hashMap.containsKey(file.getName())) { // keyword found in the file name then increment the occurrence count
					int n = hashMap.get(file.getName());
					hashMap.put(file.getName(), n+1);
					index.put(word, hashMap);
				}
				else {						
					hashMap.put(file.getName(), 1);   //if not found then initialize the value  to 1 i.e. no of occurrence to 1
					index.put(word, hashMap);
				}
			}
			else {
				HashMap<String, Integer> hashMap = new HashMap<String, Integer>(); // if the word is not present in the external hash map then add the word
																						//in the hash-map and initailze the occurence as 1
				hashMap.put(file.getName(), 1);
				index.put(word, hashMap);
			}
		}
	}
}

