package referencedclasses;

public class SpellChecker {
	public static void spellCheck(String misSpelled) {
		if(Suggestions.tst.contains(misSpelled)) {
			System.out.println("Spelling successfully found in dictionary");
			System.out.println(Suggestions.tst.get(misSpelled));
			return;
		}
		
		Queue<String> strings =  (Queue<String>) Suggestions.tst.prefixMatch(misSpelled.substring(0,1)); // checking only the first letter of the word in the tst
																										// for prefix match
		int[] distance = new int[strings.size()]; // calculating the edit distance for all the suggested word
		int i = 0; 
		boolean found = false; // to check if the word is found
		for (String string : strings) { 
			distance[i++] = Sequences.editDistance(misSpelled, string); //store all the editdistance with the misspelled word in an array
			if(distance[i-1] == 1) { // check if the edit distance is 1, then only show the suggestions with edit distance as 1 no other words,
															//since the list will be long 
				found=true;
				System.out.println("Did you mean " + string + " ?");
			}
		}
		
		if(found == false) //word not found
			System.out.println("Sorry!! No such words found.");
	}
}
